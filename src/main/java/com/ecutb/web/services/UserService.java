package com.ecutb.web.services;

import com.ecutb.web.entities.User;
import com.ecutb.web.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String username){
        var found = userRepository.findById(username);
        return found.orElse(null);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        var found = findById(user.getUsername());
        if (found.isPresent){
            return userRepository.save(user);
        }else return null;
    }

    public boolean delete(String username){
        var found = userRepository.findById(username);
        found.ifPresent(user -> userRepository.delete(user));
        return !userRepository.findById(username).isPresent();
    }



}

