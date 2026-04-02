package com.example.bugle_be.domain.auth.domain.repository;

import com.example.bugle_be.domain.auth.domain.BlackListToken;
import org.springframework.data.repository.CrudRepository;

public interface BlackListTokenRepository extends CrudRepository<BlackListToken, String> {
}
