package marcowidesott.BackPJM2W3.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import marcowidesott.BackPJM2W3.exceptions.UnauthorizedException;
import marcowidesott.BackPJM2W3.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {

    @Autowired
    private JWT jwt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 1. Legge l'header Authorization dalla richiesta
        String authHeader = request.getHeader("Authorization");

        // 2. Controlla che l'header sia presente e che inizi con "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // passa al prossimo filtro se l'header non è valido
            return;
        }

        // 3. Estrae il token dall'header
        String accessToken = authHeader.substring(7); // rimuove "Bearer " per ottenere il token

        try {
            // 4. Verifica la validità del token
            String username = jwt.validateToken(accessToken);

            // 5. Se il token è valido, imposta l'autenticazione nel SecurityContext
            if (username != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (UnauthorizedException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
            return;
        }

        // 6. Procede al prossimo filtro nella catena
        filterChain.doFilter(request, response);
    }
}

