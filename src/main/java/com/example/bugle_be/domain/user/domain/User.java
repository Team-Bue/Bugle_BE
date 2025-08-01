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
    private String email; // email validation 사용하기

    @Column(columnDefinition = "VARCHAR(60)")
    private String password; // 특수문자 @,#,!,%,&,* 포함

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String accountId; // .으로 시작하거나 .으로 끝나면 안됨, 최소 4자 최대 20자, [영문, 숫자, 언더바, 점]만 가능함 @Pattern 사용 고려

    @Column(columnDefinition = "VARCHAR(20)")
    private String userName; // 최소 제한 없음, 최대 20자
}
