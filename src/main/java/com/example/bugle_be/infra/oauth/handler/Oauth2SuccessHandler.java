package com.example.bugle_be.infra.oauth.handler;

import com.example.bugle_be.domain.auth.presentation.dto.response.TokenResponse;
import com.example.bugle_be.global.security.jwt.JwtTokenProvider;
import com.example.bugle_be.infra.oauth.factory.Oauth2UserInfoFactory;
import com.example.bugle_be.infra.oauth.info.Oauth2UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();

        Oauth2UserInfo userInfo = Oauth2UserInfoFactory.getOauth2UserInfo(
            registrationId,
            oAuth2User.getAttributes()
        );

        TokenResponse tokenResponse = jwtTokenProvider.createToken(userInfo.getEmail());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(tokenResponse));
    }
}
