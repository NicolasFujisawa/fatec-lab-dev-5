package fatec.labdev.projeto.pollingapp.config.jwt;

import fatec.labdev.projeto.pollingapp.user.UserLoginDto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

    private static final long JWT_VALIDITY_HOURS = 4L;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) throws JsonProcessingException {
        Date now = new Date(System.currentTimeMillis());
        Date expirationTime = new Date(System.currentTimeMillis() + JWT_VALIDITY_HOURS * 60 * 60 * 1000);

        ObjectMapper mapper = new ObjectMapper();
        UserLoginDto userLoginDto = UserLoginDto
                .builder()
                .username(userDetails.getUsername())
                .role(userDetails.getAuthorities().iterator().next().getAuthority())
                .build();

        String token = mapper.writeValueAsString(userLoginDto);

        return Jwts.builder()
                .claim("user", token)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS512, this.secret)
                .compact();
    }

    public Authentication parseToken(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("user", String.class);

        UserLoginDto userLogin = mapper.readValue(credentialsJson, UserLoginDto.class);
        UserDetails userDetails = User
                .builder()
                .username(userLogin.getUsername())
                .password("secret")
                .authorities(userLogin.getRole())
                .build();

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities());
    }
}
