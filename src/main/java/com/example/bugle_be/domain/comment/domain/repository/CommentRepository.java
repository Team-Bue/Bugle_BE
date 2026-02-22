package com.example.bugle_be.domain.comment.domain.repository;

import com.example.bugle_be.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {
}
