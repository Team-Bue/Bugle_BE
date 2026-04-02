package com.example.bugle_be.domain.auth.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlackListToken {

    @Id
    private String accessToken;

    @TimeToLive
    private Long ttl;

    public static BlackListToken of(String accessToken, Long ttl) {
        return BlackListToken.builder()
            .accessToken(accessToken)
            .ttl(ttl)
            .build();
    }
}
