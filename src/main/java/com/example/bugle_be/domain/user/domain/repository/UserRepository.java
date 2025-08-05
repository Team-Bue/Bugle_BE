package com.example.bugle_be.domain.user.domain.repository;

import com.example.bugle_be.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByEmail(String email);

    Optional<User> findByAccountId(String accountId);

    Boolean existsByEmail(String email);

    Boolean existsByAccountId(String accountId);
}
