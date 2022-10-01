package com.moyu.oauth2.client.utils;

import com.moyu.oauth2.client.manager.context.OAuth2LoginPostProcessorContext;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

public class JwtUtils {
    private static JwtEncoder jwtEncoder;

    static {
        try {
            jwtEncoder = new NimbusJwtEncoder(jwkSource());
        } catch (Exception e) {
            jwtEncoder = null;
        }
    }

    public static Jwt generate(OAuth2LoginPostProcessorContext context) {
        assert jwtEncoder != null : "jwtEncoder 不存在";

        Instant issuedAt = Instant.now();
        Instant expiresAt;
        // TODO 过期时间？
        expiresAt = issuedAt.plus(12L, ChronoUnit.DAYS);

        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder();

        claimsBuilder
                .subject(String.valueOf(context.getUserBasicInfo().getId()))
                .audience(Collections.singletonList("moyu"))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .claim("scope", context.getAuthorities())
                .claim("user", context.getUserBasicInfo());

        JwsHeader.Builder headersBuilder = JwsHeader.with(SignatureAlgorithm.RS256);
        JwsHeader headers = headersBuilder.build();
        JwtClaimsSet claims = claimsBuilder.build();

        return jwtEncoder.encode(JwtEncoderParameters.from(headers, claims));
    }

    private static JWKSource<SecurityContext> jwkSource() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, JOSEException {
        // 证书的路径
        String path = "keypair/moyu-key.jks";
        // 证书别名
        String alias = "moyu-key";
        // keystore 密码
        String password = "123456";

        ClassPathResource resource = new ClassPathResource(path);
        KeyStore jks = KeyStore.getInstance("jks");
        char[] pin = password.toCharArray();
        jks.load(resource.getInputStream(), pin);
        RSAKey rsaKey = RSAKey.load(jks, alias, pin);

        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }
}
