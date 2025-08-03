package com.example.bugle_be.domain.user.domain;

import com.example.bugle_be.global.entity.BaseIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Builder
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseIdEntity {

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(columnDefinition = "VARCHAR(60)")
    private String password;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String accountId;

    @Column(columnDefinition = "VARCHAR(20)")
    private String userName;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String profileImageUrl;
}
