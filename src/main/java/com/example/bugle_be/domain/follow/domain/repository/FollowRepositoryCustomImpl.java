package com.example.bugle_be.domain.follow.domain.repository;

import static com.example.bugle_be.domain.user.domain.QUser.user;
import static com.example.bugle_be.domain.follow.domain.QFollow.follow;

import com.example.bugle_be.domain.follow.presentation.dto.response.FollowResponse;
import com.example.bugle_be.domain.follow.presentation.dto.response.QFollowResponse_UserDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<FollowResponse.UserDto> findAllFollowersByUserId(Long userId) {
        return queryFactory
            .select(
                new QFollowResponse_UserDto(
                    user.id,
                    user.accountId,
                    user.userName,
                    user.profileImageUrl
                )
            )
            .from(follow)
            .join(follow.follower, user)
            .where(follow.following.id.eq(userId))
            .orderBy(follow.createdAt.desc())
            .fetch();
    }

    @Override
    public Long countFollowersByUserId(Long userId) {
        return Optional.ofNullable(
            queryFactory
                .select(follow.count())
                .from(follow)
                .where(follow.following.id.eq(userId))
                .fetchOne()
        ).orElse(0L);
    }

    @Override
    public List<FollowResponse.UserDto> findAllFollowingsByUserId(Long userId) {
        return queryFactory
            .select(
                new QFollowResponse_UserDto(
                    user.id,
                    user.accountId,
                    user.userName,
                    user.profileImageUrl
                )
            )
            .from(follow)
            .join(follow.following, user)
            .where(follow.follower.id.eq(userId))
            .orderBy(follow.createdAt.desc())
            .fetch();
    }

    @Override
    public Long countFollowingsByUserId(Long userId) {
        return Optional.ofNullable(
            queryFactory
                .select(follow.count())
                .from(follow)
                .join(follow.following, user)
                .where(follow.follower.id.eq(userId))
                .fetchOne()
        ).orElse(0L);
    }
}
