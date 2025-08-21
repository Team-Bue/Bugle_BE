package com.example.bugle_be.domain.mail.domain.repository;

import com.example.bugle_be.domain.mail.domain.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, String> {
}
