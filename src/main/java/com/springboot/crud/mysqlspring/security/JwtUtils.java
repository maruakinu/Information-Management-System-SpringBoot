package com.springboot.crud.mysqlspring.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final Long validSeconds;
    private final Key key;

    public JwtUtils(String signKey, Long validSeconds) {
        this.validSeconds = validSeconds;
        key = Keys.hmacShaKeyFor(signKey.getBytes(StandardCharsets.UTF_8));

    }

    public String encode(String sub) {
        if (sub == null || sub.equals("")) {
            return null;
        }
        Instant exp = Instant.now();
        logger.info(String.valueOf("This is the Current Date: " + new Date(exp.toEpochMilli())));
        logger.info(String.valueOf("This is the Expiry Token Date: " + new Date(exp.toEpochMilli() + validSeconds)));
        //The Token will be expired on 1-hour timeframe
        return Jwts.builder().setSubject(sub).setIssuedAt(new Date(exp.toEpochMilli())).setExpiration(new Date(exp.toEpochMilli() + validSeconds)).signWith(key).compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            Instant now = Instant.now();
            Date exp = claims.getExpiration();
            return exp.after(Date.from(now));
        } catch (JwtException e) {
            return false;
        }
    }

    public String getSub(String jwt) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            return claims.getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

}
