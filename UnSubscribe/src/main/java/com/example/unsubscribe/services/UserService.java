package com.example.unsubscribe.services;

import com.example.unsubscribe.dto.AppUserDTO;
import com.example.unsubscribe.exceptions.UserAlreadyExistException;
import com.example.unsubscribe.exceptions.WrongFormatException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<AppUserDTO> getAll();

    Optional<AppUserDTO> getById(Integer id);

    AppUserDTO save(AppUserDTO appUserDTO) throws UserAlreadyExistException, WrongFormatException;

    void delete(Integer id);
}
