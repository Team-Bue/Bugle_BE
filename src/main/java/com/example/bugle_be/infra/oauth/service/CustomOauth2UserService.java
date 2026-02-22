package com.example.bugle_be.infra.oauth.service;

import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.domain.user.domain.repository.UserRepository;
import com.example.bugle_be.global.security.auth.AuthDetails;
import com.example.bugle_be.infra.oauth.factory.Oauth2UserInfoFactory;
import com.example.bugle_be.infra.oauth.info.Oauth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        try {
            return process(userRequest, oAuth2User);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest request, OAuth2User oAuth2User) {
        Oauth2UserInfo userInfo = Oauth2UserInfoFactory.getOauth2UserInfo(
            request.getClientRegistration().getRegistrationId(),
            oAuth2User.getAttributes()
        );

        return new AuthDetails(register(userInfo), oAuth2User.getAttributes());
    }

    private User register(Oauth2UserInfo userInfo) {
        return userRepository.findByEmail(userInfo.getEmail())
            .orElseGet(() -> {
                User newUser = User.builder()
                    .email(userInfo.getEmail())
                    .accountId(userInfo.getAccountId())
                    .build();
                return userRepository.save(newUser);
            });
    }
}
