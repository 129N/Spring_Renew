package org.mik.yftwrg.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class JWTService {

    private static final long EXPIRATION_TIME = 86400000; // 1 day in ms
    private static final String SECRET = "SuperSecretKeyToSignJWTsSuperSecretKeyToSignJWTs"; // Must be at least 32 chars
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("authorities",  List.of("ROLE_" + role))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

//    public String extractRole(String token){
//        return (String) Jwts.parserBuilder()
//                .setSigningKey(key).build()
//                .parseClaimsJws(token)
//                .getBody()
//                .get("role");
//    }

    public List<SimpleGrantedAuthority> extractAuthorities(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody();

        List<String> roles = claims.get("authorities", List.class);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}




//
//    private final PasswordEncoder passwordEncoder;
//
//    @Value("${spring.security.jwt.secret}")
//    private String secretKey;
//
//    @Value("${spring.security.jwt.expiration}")
//    private long expirationTime; // in milliseconds
//
//    private Key signingKey;
//
//    @PostConstruct
//    public void init(){
//        // Validate secret key length (must be at least 256 bits/32 chars)
//        if (secretKey == null || secretKey.length() < 32) {
//            throw new IllegalStateException(
//                    "JWT secret key must be at least 256 bits (32 characters) long. " +
//                            "Current length: " + secretKey.length() + " characters"
//            );
//        }
//        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public String generateToken(String username) {
//        return generateToken(new HashMap<>(), username);
//    }
//
//    public String generateToken(Map<String, Object> extraClaims, String username) {
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(signingKey, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//    public boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
//        final Claims claims = extractAllClaims(token);
//        return resolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        try {
//            return Jwts.parserBuilder()
//                    .setSigningKey(signingKey)
//                    .build()
//                    .parseClaimsJws(StringUtils.trimAllWhitespace(token))
//                    .getBody();
//        } catch (Exception e) {
//            log.error("Error parsing JWT token: {}", e.getMessage());
//            throw new JwtException("Invalid JWT token");
//        }
//    }

