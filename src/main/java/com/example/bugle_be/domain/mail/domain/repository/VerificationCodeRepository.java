package com.example.bugle_be.domain.mail.domain.repository;

import com.example.bugle_be.domain.mail.domain.VerificationCode;
import org.springframework.data.repository.CrudRepository;

public interface VerificationCodeRepository extends CrudRepository<VerificationCode, String> {
}
