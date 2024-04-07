package fr.arinonia.openjupdate.service.impl;

import fr.arinonia.openjupdate.entity.User;
import fr.arinonia.openjupdate.repository.UserRepository;
import fr.arinonia.openjupdate.response.Response;
import fr.arinonia.openjupdate.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Response registerUser(final String username, final String password, final String cPassword) {
        final Response response = new Response();

        if (!password.equals(cPassword)) {
            response.setSuccess(false);
            response.setErrorMessage("Passwords do not match.");
            return response;
        }

        if (password.length() < 7) {
            response.setSuccess(false);
            response.setErrorMessage("Your password size must be higher than 7");
            return response;
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            response.setSuccess(false);
            response.setErrorMessage("User already exists with the same name");
            return response;
        }

        final User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(this.passwordEncoder.encode(password));
        this.userRepository.save(newUser);
        response.setSuccess(true);
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
