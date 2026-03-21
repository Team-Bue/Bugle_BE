package com.example.bugle_be.domain.user.domain;

import com.example.bugle_be.global.entity.BaseTimeEntity;
import com.example.bugle_be.global.util.ImageProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

@Getter
@Builder
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(columnDefinition = "VARCHAR(60)")
    private String password;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String accountId;

    @Column(columnDefinition = "VARCHAR(20)")
    private String userName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String deviceToken;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String profileImageObjectKey = ImageProperty.DEFAULT_USER_PROFILE_IMAGE;

    public void changePassword(String password) {
        this.password = password;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public void deleteDeviceToken() {
        this.deviceToken = null;
    }

    public void update(String accountId, String userName, String profileImageObjectKey) {
        this.accountId = accountId;
        this.userName = userName;
        this.profileImageObjectKey = profileImageObjectKey;
    }

    public boolean isCustomProfileImage() {
        return !this.profileImageObjectKey.equals(ImageProperty.DEFAULT_USER_PROFILE_IMAGE);
    }
}
