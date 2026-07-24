package com.bang.authservice.repository;

import com.bang.authservice.entity.Role;
import com.bang.authservice.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface  UserRepository extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = {
            "roles",
            "roles.permissions"
    })
    Optional<User> findByUserName(String userName);

    @EntityGraph(attributePaths = {
            "roles",
            "roles.permissions"
    })
    Optional<User> findById(String id);

    @EntityGraph(attributePaths = {
            "roles",
            "roles.permissions"
    })
    List<User> findAll();

    Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
