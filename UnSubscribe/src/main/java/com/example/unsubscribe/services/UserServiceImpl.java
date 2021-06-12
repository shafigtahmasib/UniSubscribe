package com.example.unsubscribe.services;

import com.example.unsubscribe.dao.UserDAO;
import com.example.unsubscribe.dto.AppUserDTO;
import com.example.unsubscribe.exceptions.UserAlreadyExistException;
import com.example.unsubscribe.exceptions.WrongFormatException;
import com.example.unsubscribe.models.AppUser;
import com.example.unsubscribe.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AppUserDTO> getAll() {
        return userDAO.getAll().stream().map(i -> modelMapper.map(i, AppUserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<AppUserDTO> getById(Integer id) {
        return Optional.of(modelMapper.map(userDAO.getById(id), AppUserDTO.class));
    }

    @Override
    public AppUserDTO save(AppUserDTO appUserDTO) throws UserAlreadyExistException, WrongFormatException {
        if(!appUserDTO.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            throw new IllegalStateException();
        }

        else if (appUserDTO.getId() != null && userDAO.getById(appUserDTO.getId()).isPresent()) {
            userDAO.save(modelMapper.map(appUserDTO, AppUser.class));
        } else if (emailExists(appUserDTO.getEmail())) {
            throw new UserAlreadyExistException();
        } else {
            userDAO.save(modelMapper.map(appUserDTO, AppUser.class));
        }
        return appUserDTO;
    }

    private boolean emailExists(String email) {
        if (userRepository.findByEmail(email) != null) return true;
        return false;
    }

    private boolean validEmail(String email) throws WrongFormatException {
        if (!email.matches(" ^[\\w!#$%&amp;’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            throw new WrongFormatException();
        } else {
            return true;
        }
    }

    @Override
    public void delete(Integer id) {
        userDAO.delete(id);
    }
}
