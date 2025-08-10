package com.example.bugle_be.global.security.jwt;

import com.example.bugle_be.domain.auth.domain.RefreshToken;
import com.example.bugle_be.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.global.exception.ExpiredJwt;
import com.example.bugle_be.global.exception.InvalidJwt;
import com.example.bugle_be.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    private SecretKey secretKey;

    private static final String ACCESS = "access";
    private static final String REFRESH = "refresh";

    @PostConstruct
    public void initSecretKey() {
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8));
    }

    private String generateToken(String email, String type, Long exp) {
        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(exp)))
            .signWith(secretKey, Jwts.SIG.HS256)
            .claim("type", type)
            .compact();
    }

    private String generateAccessToken(String email) {
        return generateToken(email, ACCESS, jwtProperties.accessExp());
    }

    private String generateRefreshToken(String email) {
        String refreshToken = generateToken(email, REFRESH, jwtProperties.refreshExp());

        refreshTokenRepository.save(
            RefreshToken.builder()
                .email(email)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.refreshExp())
                .build()
        );

        return refreshToken;
    }

    public TokenResponse createToken(String email) {
        return TokenResponse.builder()
            .accessToken(generateAccessToken(email))
            .refreshToken(generateRefreshToken(email))
            .accessExp(LocalDateTime.now().plusSeconds(jwtProperties.accessExp()))
            .refreshExp(LocalDateTime.now().plusSeconds(jwtProperties.refreshExp()))
            .build();
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.prefix())) {
            return bearerToken.replace(jwtProperties.prefix(), "").trim();
        }
        return null;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.header());
        return parseToken(bearerToken);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (Exception e) {
            if (e instanceof io.jsonwebtoken.ExpiredJwtException) {
                throw ExpiredJwt.EXCEPTION;
            } else {
                throw InvalidJwt.EXCEPTION;
            }
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Claims claims = getClaims(token);
            return REFRESH.equals(claims.get("type"));
        } catch (Exception e) {
            return false;
        }
    }

    private String getTokenSubject(String token) {
        return getClaims(token).getSubject();
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
