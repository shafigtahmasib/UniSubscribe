package com.example.unsubscribe.repositories;

import com.example.unsubscribe.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    @Query(value = "SELECT u FROM appusers u WHERE u.email=:email")
    AppUser findByEmail(String email);
}
