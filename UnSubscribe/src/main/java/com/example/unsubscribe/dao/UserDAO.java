package com.example.unsubscribe.dao;

import com.example.unsubscribe.dto.AppUserDTO;
import com.example.unsubscribe.models.AppUser;
import com.example.unsubscribe.models.Subscription;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<AppUser> getAll();

    Optional<AppUser> getById(Integer id);

    AppUser save(AppUser user);

    void delete(Integer id);
}
