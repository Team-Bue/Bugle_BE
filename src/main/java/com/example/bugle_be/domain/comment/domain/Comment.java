package com.example.bugle_be.domain.comment.domain;

import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.user.domain.User;
import com.example.bugle_be.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

@Getter
@Builder
@Entity(name = "tbl_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(300)")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
