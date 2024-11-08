package marcowidesott.BackPJM2W3.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import marcowidesott.BackPJM2W3.entities.Role;
import marcowidesott.BackPJM2W3.entities.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWT {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000L; // 1 giorno

    // Genera un token JWT
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername()) // Usa lo username dell'utente come subject
                .claim("roles", user.getRoles().stream().map(Role::getRoleName).toList()) // Aggiungi i ruoli come claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }


    // Verifica il token JWT e ottieni il nome utente
    public String validateToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
