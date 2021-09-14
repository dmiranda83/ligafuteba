package br.com.futeba.services;

import java.util.List;
import java.util.Optional;

import br.com.futeba.models.User;

public interface UserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> login(String email, String password);

    User update(User currentUser);

}
