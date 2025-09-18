package com.quynhtien.cineasy.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.quynhtien.cineasy.dto.request.TokenRequest;
import com.quynhtien.cineasy.dto.request.AuthenticationRequest;
import com.quynhtien.cineasy.dto.request.RefreshTokenRequest;
import com.quynhtien.cineasy.dto.response.IntrospectResponse;
import com.quynhtien.cineasy.dto.response.AuthenticationResponse;
import com.quynhtien.cineasy.entity.LoggedOutToken;
import com.quynhtien.cineasy.entity.User;
import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.repository.LoggedOutTokenRepository;
import com.quynhtien.cineasy.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    @NonFinal
    @Value("${jwt.secretKey}")
    protected String SECRET_KEY;

    @NonFinal
    @Value("${jwt.durationMs}")
    protected Long DURATION_MS;

    @NonFinal
    @Value("${jwt.refreshDurationMs}")
    protected Long REFRESH_DURATION_MS;

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    LoggedOutTokenRepository loggedOutTokenRepository;

    //login
    public AuthenticationResponse login(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.INVALID_USER_INFO);
        }

        String token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    //introspect token
    public IntrospectResponse introspectToken(TokenRequest request) {
        verifyToken(request.getToken(), false);
        boolean valid = true;
        return IntrospectResponse.builder()
                    .valid(valid)
                    .build();
    }

    //log out token
    public String logout(TokenRequest request) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(request.getToken());
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());

            boolean valid = signedJWT.verify(verifier);

            Date refreshableTime = new Date(signedJWT.getJWTClaimsSet().getIssueTime().getTime()
                    + REFRESH_DURATION_MS);
            if (valid && new Date().before(refreshableTime)) {
                String jti = signedJWT.getJWTClaimsSet().getJWTID();
                if (loggedOutTokenRepository.existsById(jti)) {
                    return "Already logged out successfully";
                } else {
                    //save jti to db
                    LoggedOutToken loggedOutToken = LoggedOutToken.builder()
                            .tokenId(jti)
                            .expirationTime(refreshableTime.toString())
                            .build();
                    loggedOutTokenRepository.save(loggedOutToken);
                    return "Log out successfully";
                }
            } else {
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
        } catch (Exception e) {
            log.error("Error while logging out: {}", e.getMessage());
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    //refresh token
    public AuthenticationResponse refreshToken(TokenRequest request) {
        SignedJWT signedJWT = verifyToken(request.getToken(), true);
        try {
            String username = signedJWT.getJWTClaimsSet().getSubject();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            String newToken = generateToken(user);
            return AuthenticationResponse.builder()
                    .token(newToken)
                    .build();
        } catch (ParseException e) {
            log.error("Error while parsing token: {}", e.getMessage());
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    public SignedJWT verifyToken(String token, boolean isRefresh) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            Date refreshableTime = new Date(signedJWT.getJWTClaimsSet().getIssueTime().getTime()
                    + REFRESH_DURATION_MS);

            boolean valid = signedJWT.verify(verifier);

            //not refresh
            if (!isRefresh) {
                if (valid && new Date().before(expirationTime) ){
                    return signedJWT;
                } else {
                    throw new AppException(ErrorCode.INVALID_TOKEN);
                }
            }

            //refresh token
            if (valid && new Date().before(refreshableTime )) {
                //check if jti is in logged out db
                String jti = signedJWT.getJWTClaimsSet().getJWTID();
                if (loggedOutTokenRepository.existsById(jti)) {
                    throw new AppException(ErrorCode.INVALID_TOKEN);
                }
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
                .expirationTime(new Date(new Date().getTime() + DURATION_MS)) //1 hour
                .claim("userId", user.getId())
                .claim("scope", buildScope(user))
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

    private String buildScope(User user) {
        StringBuilder scope = new StringBuilder();
        user.getRoles().forEach(role -> {
            scope.append("ROLE_").append(role.getName()).append(" ");
            role.getPermissions().forEach(permission ->
                    scope.append(permission.getName()).append(" "));
        });
        return scope.toString().trim();
    }
}
