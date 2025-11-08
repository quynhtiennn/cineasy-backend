package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.entity.EmailVerificationToken;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.repository.EmailVerificationTokenRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class EmailVerificationTokenService {
    EmailVerificationTokenRepository emailVerificationTokenRepository;

    public EmailVerificationToken createToken(User user) {
        EmailVerificationToken token = EmailVerificationToken.builder()
                .user(user)
                .expiryTime(LocalDateTime.now().plusHours(24))
                .build();
        return emailVerificationTokenRepository.save(token);
    }

    public User verifyToken(UUID emailVerificationTokenId) {
        EmailVerificationToken token = emailVerificationTokenRepository.findById(emailVerificationTokenId)
                .orElseThrow(()-> new AppException(ErrorCode.INVALID_EMAIL_VERIFICATION));
        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.INVALID_EMAIL_VERIFICATION);
        }
        User user = token.getUser();
        user.setEnabled(true);
        user.setEmailVerificationToken(null);
        return user;
    }




}
