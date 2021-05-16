package fatec.labdev.projeto.pollingapp.config.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (requestTokenHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = getToken(requestTokenHeader);

        try {
            doAuthentication(jwtToken);
            filterChain.doFilter(request, response);
        } catch (Throwable t) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), t.getMessage());
        }
    }

    private static String getToken(String authHeader) {
        return authHeader.replaceAll("Bearer ", "");
    }

    private void doAuthentication(String jwtToken) throws JsonProcessingException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            log.info("Context already authenticated");
        }

        Authentication auth = jwtUtil.parseToken(jwtToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info(auth.getName() + " has Authenticated ");
    }
}
