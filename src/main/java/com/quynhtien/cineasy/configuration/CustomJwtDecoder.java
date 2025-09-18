package com.quynhtien.cineasy.configuration;

import com.quynhtien.cineasy.exception.AppException;
import com.quynhtien.cineasy.exception.ErrorCode;
import com.quynhtien.cineasy.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Autowired
    AuthenticationService authenticationService;

    NimbusJwtDecoder jwtDecoder;

    @Value("${jwt.secretKey}")
    String SECRET_KEY;

    @Override
    public Jwt decode(String token) {
        try {
            authenticationService.verifyToken(token, false);
        } catch (Exception e) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(),"HS512");
        jwtDecoder = NimbusJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
        return jwtDecoder.decode(token);
    }
}
