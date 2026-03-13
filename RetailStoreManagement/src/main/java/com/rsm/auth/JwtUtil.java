package com.rsm.auth;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

	 private final String SECRET = "this-is-my-secret-key-fully-secured-by-2143658709"; // 32+ chars
	    private final long EXPIRATION_MS = 24 * 60 * 60 * 1000; // 1 day

	    private Key getSigningKey() {
	        return Keys.hmacShaKeyFor(SECRET.getBytes());
	    }

	    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
	        return Jwts.builder()
	                .setClaims(extraClaims)
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	        Claims claims = Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	        return resolver.apply(claims);
	    }

	    public boolean isTokenValid(String token, UserDetails userDetails) {
	        final String username = extractUsername(token);
	        return username.equals(userDetails.getUsername()) &&
	               !isTokenExpired(token);
	    }

	    private boolean isTokenExpired(String token) {
	        Date exp = extractClaim(token, Claims::getExpiration);
	        return exp.before(new Date());
	    }
}
