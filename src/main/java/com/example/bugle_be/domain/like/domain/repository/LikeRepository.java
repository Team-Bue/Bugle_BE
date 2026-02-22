package com.example.bugle_be.domain.like.domain.repository;

import com.example.bugle_be.domain.like.domain.Like;
import com.example.bugle_be.domain.post.domain.Post;
import com.example.bugle_be.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    int deleteByUserAndPost(User user, Post post);
}
