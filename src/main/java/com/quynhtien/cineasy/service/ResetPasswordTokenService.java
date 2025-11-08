package com.quynhtien.cineasy.service;

import com.quynhtien.cineasy.dto.request.ResetPasswordRequest;
import com.quynhtien.cineasy.entity.ResetPasswordToken;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.repository.ResetPasswordTokenRepository;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ResetPasswordTokenService {
    ResetPasswordTokenRepository resetPasswordTokenRepository;
    PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ResetPasswordToken createToken(User user) {
        ResetPasswordToken token = ResetPasswordToken.builder()
                .user(user)
                .expiryTime(LocalDateTime.now().plusMinutes(30))
                .build();
        return resetPasswordTokenRepository.save(token);
    }

    public boolean verifyToken(UUID resetPasswordTokenId) {
        ResetPasswordToken token = resetPasswordTokenRepository.findById(resetPasswordTokenId)
                .orElseThrow(()-> new AppException(ErrorCode.INVALID_RESET_PASSWORD_TOKEN));
        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.INVALID_RESET_PASSWORD_TOKEN);
        }
        return true;
    }

    public String resetPassword(ResetPasswordRequest request) {
        ResetPasswordToken token = resetPasswordTokenRepository.findById(UUID.fromString(request.getToken()))
                .orElseThrow(()-> new AppException(ErrorCode.INVALID_RESET_PASSWORD_TOKEN));
        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new AppException(ErrorCode.INVALID_RESET_PASSWORD_TOKEN);
        }
        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmailVerificationToken(null);
        userRepository.save(user);
        return "Password reset successfully";
    }

}
