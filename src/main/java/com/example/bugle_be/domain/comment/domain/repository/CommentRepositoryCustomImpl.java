package com.example.bugle_be.domain.comment.domain.repository;

import static com.example.bugle_be.domain.comment.domain.QComment.comment;
import static com.example.bugle_be.domain.user.domain.QUser.user;
import static com.example.bugle_be.domain.post.domain.QPost.post;

import com.example.bugle_be.domain.comment.presentation.dto.response.CommentsResponse;
import com.example.bugle_be.domain.comment.presentation.dto.response.QCommentsResponse_CommentDto;
import com.example.bugle_be.domain.comment.presentation.dto.response.QCommentsResponse_CommentDto_UserDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentsResponse.CommentDto> findAll(Long postId) {
        return queryFactory
            .select(
                new QCommentsResponse_CommentDto(
                    comment.id,
                    comment.content,
                    new QCommentsResponse_CommentDto_UserDto(
                        comment.user.id,
                        comment.user.accountId,
                        comment.user.profileImageUrl
                    )
                )
            )
            .from(comment)
            .join(comment.post, post)
            .join(comment.user, user)
            .where(post.id.eq(postId))
            .orderBy(comment.createdAt.desc())
            .fetch();
    }
}
