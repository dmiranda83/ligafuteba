package br.com.futeba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.futeba.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE email = :email AND password = :password", nativeQuery = true)
    public Optional<User> login(@Param("email") String email, @Param("password") String password);
}
