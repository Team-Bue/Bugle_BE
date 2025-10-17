package com.example.bugle_be.domain.follow.domain.repository;

import com.example.bugle_be.domain.follow.domain.Follow;
import com.example.bugle_be.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long>, FollowRepositoryCustom {

    boolean existsByFollowerAndFollowing(User follower, User following);

    int deleteByFollowerAndFollowing(User follower, User following);
}
