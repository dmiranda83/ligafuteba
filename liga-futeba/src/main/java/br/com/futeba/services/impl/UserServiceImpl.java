package br.com.futeba.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.User;
import br.com.futeba.repositories.UserRepository;
import br.com.futeba.services.PasswordService;
import br.com.futeba.services.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordService passwordService;

    @Override
    public User save(final User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> login(final String email, final String password) {
        return repository.login(email, passwordService.getSecureHash(password));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public User update(final User user) {
        return repository.saveAndFlush(user);
    }
}
