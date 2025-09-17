package com.quynhtien.cineasy.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.quynhtien.cineasy.dto.request.AuthenticationIntrospectRequest;
import com.quynhtien.cineasy.dto.request.AuthenticationLoginRequest;
import com.quynhtien.cineasy.dto.request.UserCreationRequest;
import com.quynhtien.cineasy.dto.request.UserUpdateRequest;
import com.quynhtien.cineasy.dto.response.AuthenticationIntrospectResponse;
import com.quynhtien.cineasy.dto.response.AuthenticationLoginResponse;
import com.quynhtien.cineasy.dto.response.UserResponse;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.mapper.UserMapper;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    @NonFinal
    @Value("${jwt.secretKey}")
    protected String SECRET_KEY;

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    //login
    public AuthenticationLoginResponse login(AuthenticationLoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.INVALID_USER_INFO);
        }

        String token = generateToken(user);

        return AuthenticationLoginResponse.builder()
                .token(token)
                .build();
    }

    //introspect token
    public AuthenticationIntrospectResponse introspectToken(AuthenticationIntrospectRequest request) {
        boolean valid = verifyToken(request.getToken()) != null;
        return AuthenticationIntrospectResponse.builder()
                    .valid(valid)
                    .build();
    }

    private SignedJWT verifyToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
            if (signedJWT.verify(verifier)){
                return signedJWT;
            } else {
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
        } catch (Exception e) {
            log.error("Error while verifying token: {}", e.getMessage());
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    private String generateToken(User user) {
        String token;
        //header
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

        //claimset/payload
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .jwtID(UUID.randomUUID().toString())
                .issuer("quynhtien")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60)) //1 hour
                .claim("userId", user.getId())
                .claim("scope", "USER")
                .build();

        //jwt
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);
        //sign
        try {
            signedJWT.sign(new MACSigner(SECRET_KEY)); //32 chars
            token = signedJWT.serialize();
            return token;
        } catch (Exception e) {
            log.error("Error while generating token: {}", e.getMessage());
            throw new AppException(ErrorCode.GENERATE_TOKEN_ERROR);
        }

    }
}
