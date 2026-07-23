package com.bang.authservice.repository;

import com.bang.authservice.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @EntityGraph(attributePaths = "permissions")
    List<Role> findAll();
}