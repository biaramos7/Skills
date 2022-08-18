package com.serratec.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${auth.jwt-secret}")
    public String TOKEN_SECRET;

    @Value("${auth.jwt-expiration-milliseg}")
    public Long TOKEN_EXPIRATION;

    @Autowired
    private UserDetalheService service;

    public String generateToken(String username) {
        UserDetails user = service.loadUserByUsername(username);
        return Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET.getBytes())
                .compact();
    }

    public boolean isValidToken(String token) {
        Claims claims = getClaims(token);
        if (claims == null)
            return false;
        String userName = claims.getSubject();
        Date expirationDate = claims.getExpiration();
        Date now = new Date(System.currentTimeMillis());
        if (userName != null && now.before(expirationDate)) {
            return true;
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET.getBytes()).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

    public String getUserName(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}
