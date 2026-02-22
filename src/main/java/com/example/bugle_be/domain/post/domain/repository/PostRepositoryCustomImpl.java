package com.example.bugle_be.domain.post.domain.repository;

import static com.example.bugle_be.domain.post.domain.QPost.post;

import com.example.bugle_be.domain.post.presentation.dto.response.PostsResponse;
import com.example.bugle_be.domain.post.presentation.dto.response.QPostsResponse_PostPreviewResponse;
import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.domain.search.presentation.dto.response.QPostSearchResponse_PostResponse;
import com.example.bugle_be.global.util.PageUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostsResponse.PostPreviewResponse> findAll(int page) {
        int pageSize = PageUtil.POST_DEFAULT_PAGE_SIZE;

        return queryFactory
            .select(
                new QPostsResponse_PostPreviewResponse(
                    post.id,
                    post.user.accountId,
                    post.user.profileImageObjectKey,
                    post.country,
                    post.region,
                    post.objectKey,
                    post.content
                )
            )
            .from(post)
            .orderBy(post.createdAt.desc(), post.id.desc())
            .offset((long) (page - 1) * pageSize)
            .limit(pageSize)
            .fetch();
    }

    @Override
    public Long countAll() {
        return queryFactory
            .select(post.count())
            .from(post)
            .fetchOne();
    }

    @Override
    public List<PostSearchResponse.PostResponse> findAllByTypeAndKeyword(int page, SearchType type, String keyword) {
        int pageSize = PageUtil.POST_DEFAULT_PAGE_SIZE;

        BooleanExpression condition = switch (type) {
            case CONTENT -> post.content.containsIgnoreCase(keyword);
            case LOCATION -> post.country.containsIgnoreCase(keyword)
                .or(post.region.containsIgnoreCase(keyword));
        };

        return queryFactory
            .select(
                new QPostSearchResponse_PostResponse(
                    post.id,
                    post.objectKey
                )
            )
            .from(post)
            .where(condition)
            .orderBy(post.createdAt.desc(), post.id.desc())
            .offset((long) (page - 1) * pageSize)
            .limit(pageSize)
            .fetch();
    }

    @Override
    public Long countByTypeAndKeywordContaining(SearchType type, String keyword) {
        BooleanExpression condition = switch (type) {
            case CONTENT -> post.content.containsIgnoreCase(keyword);
            case LOCATION -> post.country.containsIgnoreCase(keyword)
                .or(post.region.containsIgnoreCase(keyword));
        };

        return queryFactory
            .select(
                post.count()
            )
            .from(post)
            .where(condition)
            .fetchOne();
    }
}
