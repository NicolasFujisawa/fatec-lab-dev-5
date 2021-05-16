package fatec.labdev.projeto.pollingapp.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import fatec.labdev.projeto.pollingapp.common.exceptions.EntityNotFoundException;
import fatec.labdev.projeto.pollingapp.config.jwt.JwtUtil;
import fatec.labdev.projeto.pollingapp.user.controller.v1.converter.UserConverter;
import fatec.labdev.projeto.pollingapp.user.controller.v1.response.UserResponse;
import fatec.labdev.projeto.pollingapp.user.model.User;
import fatec.labdev.projeto.pollingapp.user.model.UserDetailsImpl;
import fatec.labdev.projeto.pollingapp.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User save(User user) {
        if (this.userRepository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("Username already exists");
        }
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public User update(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public User findById(UUID id) {
        return this.userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PreAuthorize("isAuthenticated()")
    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteById(UUID id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserResponse signInUser(User loginRequest) throws JsonProcessingException {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        User user = ((UserDetailsImpl) userDetails).getUser();

        UserResponse userResponse = UserConverter.convertFrom(user);
        userResponse.setToken(token);

        return userResponse;
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | LockedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User disabled or locked");
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect credentials");
        }
    }
}
