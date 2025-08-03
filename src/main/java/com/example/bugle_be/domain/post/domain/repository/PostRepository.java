package com.example.bugle_be.domain.post.domain.repository;

import com.example.bugle_be.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
