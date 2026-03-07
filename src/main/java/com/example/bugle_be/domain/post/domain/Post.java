package com.example.bugle_be.domain.post.domain;

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
@Entity(name = "tbl_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post extends BaseTimeEntity {

    @Column(nullable = false, columnDefinition = "VARCHAR(300)")
    private String content;

    @Column(columnDefinition = "VARCHAR(150)")
    private String location;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String objectKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public void update(String content, String location, String objectKey) {
        this.content = content;
        this.location = location;
        this.objectKey = objectKey;
    }
}
