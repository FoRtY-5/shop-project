package com.project.shop.config.security;

import com.project.shop.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private final String jwtIssuer = "project.com";

    private final Logger logger;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0];
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature - {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token - {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token - {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token - {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty - {}", e.getMessage());
        }
        return false;
    }

}
