package com.example.bugle_be.domain.user.domain.repository;

import static com.example.bugle_be.domain.user.domain.QUser.user;

import com.example.bugle_be.domain.search.presentation.dto.response.QUserSearchResponse_UserResponse;
import com.example.bugle_be.domain.search.presentation.dto.response.UserSearchResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserSearchResponse.UserResponse> getAllByKeyword(String keyword) {
        return queryFactory
            .select(new QUserSearchResponse_UserResponse(
                user.id,
                user.accountId,
                user.userName,
                user.profileImageUrl
            ))
            .from(user)
            .where(user.accountId.containsIgnoreCase(keyword)
                .or(user.userName.containsIgnoreCase(keyword)))
            .fetch();
    }
}
