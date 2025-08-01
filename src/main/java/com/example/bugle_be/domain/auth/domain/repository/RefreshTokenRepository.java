package com.example.bugle_be.domain.auth.domain.repository;

import com.example.bugle_be.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
