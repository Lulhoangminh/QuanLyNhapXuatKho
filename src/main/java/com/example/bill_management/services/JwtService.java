package com.example.bill_management.services;

import com.example.bill_management.entities.UserEntity;
import com.example.bill_management.repositories.UserRepository;
import com.example.bill_management.repositories.UserRoleRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${jwt.signer-key}")
    private String signerKey;

    @Value("${jwt.expiry.access-token}")
    private Long accessTokenExpiry;

    @Value("${jwt.expiry.refresh-token}")
    private Long refreshTokenExpiry;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private List<String> buildRole(String userId){
        List<String> listRoleNames = userRoleRepository.findRoleNamesByUserId(userId);
        return listRoleNames.stream().map(role -> "ROLE_"+role).toList();
    }

    private JWTClaimsSet parseToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet();
    }

    public String generateToken(UserEntity user, Long expiry) {
        // JWT include 3 parts:
        // 1. HEADER: encode alg, token type (JWT)
        // 2. PAYLOAD: info claims
        // 3. SIGNATURE: (header + payload) + signer
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("http://miruku.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(expiry, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("roles", buildRole(user.getId()))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return jwsObject.serialize(); //String token
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractUsername(String token) throws ParseException {
        return parseToken(token).getSubject();
    }

    public boolean isExpiryToken(String token) throws ParseException {
        return parseToken(token).getExpirationTime().before(new Date());
    }

    public boolean validateToken(String token, String username) throws ParseException, JOSEException {
        String extractUsername = extractUsername(token);
        return (extractUsername.equals(username) && !isExpiryToken(token) && verifyTokenSignature(token));
    }

    private boolean verifyTokenSignature(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(signerKey.getBytes());
        return signedJWT.verify(jwsVerifier);
    }

    public String getAccessToken(UserEntity user){
        return generateToken(user, accessTokenExpiry);
    }

    public String getRefreshToken(UserEntity user){
        return generateToken(user, refreshTokenExpiry);
    }
}
