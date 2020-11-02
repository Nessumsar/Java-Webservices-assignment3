package com.ecutb.web.services;

import com.ecutb.web.entities.User;
import com.ecutb.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String username){
        var found = userRepository.findById(username);
        return found.orElse(null);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user){
        var found = findById(user.getUsername());
        if (found != null) {
            return userRepository.save(user);
        }else return null;
    }

    public boolean delete(String username){
        var found = userRepository.findById(username);
        found.ifPresent(user -> userRepository.delete(user));
        return userRepository.findById(username).isEmpty();
    }



}

