package fatec.labdev.projeto.pollingapp.user.service;

import fatec.labdev.projeto.pollingapp.user.model.User;
import fatec.labdev.projeto.pollingapp.user.model.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetailsServiceImpl() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (!this.userService.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        User user = this.userService.findByUsername(username);
        return new UserDetailsImpl(user);
    }
}
