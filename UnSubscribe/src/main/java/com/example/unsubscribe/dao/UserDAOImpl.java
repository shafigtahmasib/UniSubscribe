package com.example.unsubscribe.dao;

import com.example.unsubscribe.configs.MapperConfig;
import com.example.unsubscribe.dto.AppUserDTO;
import com.example.unsubscribe.models.AppUser;
import com.example.unsubscribe.models.Role;
import com.example.unsubscribe.repositories.RoleRepository;
import com.example.unsubscribe.repositories.UserRepository;
import com.example.unsubscribe.utils.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<AppUser> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> getById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        if (roleRepository.findByName(Roles.USER).isPresent()) {
            Role role = roleRepository.findByName(Roles.USER).get();
            if (userRepository.findByEmail(appUser.getEmail()) == null) {
                role.getAppUsers().add(appUser);
                appUser.toBuilder().roles(new ArrayList(Collections.singletonList(role))).build();
            }
        }
        return userRepository.save(appUser);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
