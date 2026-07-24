package com.bang.authservice.repository;

import com.bang.authservice.entity.Role;
import com.bang.authservice.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @EntityGraph(attributePaths = "permissions")
    List<Role> findAll();

    @EntityGraph(attributePaths = "permissions")
    Optional<Role> findById(String id);

    @Override
    @EntityGraph(attributePaths = "permissions")
    List<Role> findAllById(Iterable<String> ids);
}