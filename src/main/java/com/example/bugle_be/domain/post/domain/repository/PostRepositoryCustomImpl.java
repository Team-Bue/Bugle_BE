package com.example.bugle_be.domain.post.domain.repository;

import static com.example.bugle_be.domain.post.domain.QPost.post;

import com.example.bugle_be.domain.search.presentation.dto.SearchType;
import com.example.bugle_be.domain.search.presentation.dto.response.PostSearchResponse;
import com.example.bugle_be.domain.search.presentation.dto.response.QPostSearchResponse_PostResponse;
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
    public List<PostSearchResponse.PostResponse> getAllByTypeAndKeyword(SearchType type, String keyword) {
        BooleanExpression condition = switch (type) {
            case CONTENT -> post.content.contains(keyword);
            case LOCATION -> post.country.contains(keyword)
                .or(post.region.contains(keyword));
        };

        return queryFactory
            .select(
                new QPostSearchResponse_PostResponse(
                    post.id,
                    post.fileUrl
                )
            )
            .from(post)
            .where(condition)
            .orderBy(post.createdAt.desc())
            .fetch();
    }
}
