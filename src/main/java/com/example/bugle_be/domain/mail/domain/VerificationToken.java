package com.example.bugle_be.domain.mail.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationToken {

    @Id
    private String email;

    private String token;

    @TimeToLive
    private Long ttl;

    @Builder
    public VerificationToken(String email, String token, Long ttl) {
        this.email = email;
        this.token = token;
        this.ttl = ttl;
    }
}
