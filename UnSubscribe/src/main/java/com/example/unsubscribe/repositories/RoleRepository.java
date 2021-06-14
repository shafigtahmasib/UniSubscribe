package com.example.unsubscribe.repositories;

import com.example.unsubscribe.models.Role;
import com.example.unsubscribe.utils.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(Roles name);
}
