package fatec.labdev.projeto.pollingapp.config.jwt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = getToken(requestTokenHeader);
        String username = getUsernameFromToken(jwtToken);

        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        this.doAuthentication(username, jwtToken, request);

        filterChain.doFilter(request, response);
    }

    private static String getToken(String authHeader) {
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        log.warn("JWT Token does not begin with Bearer String");
        return null;
    }

    private String getUsernameFromToken(String token) {
        if (token == null) {
            return null;
        }

        try {
            return this.jwtUtil.getUsernameFromToken(token);
        } catch (Exception exception) {
            log.info("JWT Token is invalid");
        }
        return null;
    }

    private void doAuthentication(String username, String jwtToken, HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            log.info("Context already authenticated");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

            // After setting the Authentication in the context, we specify
            // that the current user is authenticated.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            log.info(userDetails.getUsername() + " has Authenticated ");
        } else {
            log.info("JWT Token is expired");
        }
    }
}
