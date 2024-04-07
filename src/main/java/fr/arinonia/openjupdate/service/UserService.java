package fr.arinonia.openjupdate.service;

import fr.arinonia.openjupdate.entity.User;
import fr.arinonia.openjupdate.response.Response;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(final String username);
    Response registerUser(final String username, final String password, final String cPassword);

}
