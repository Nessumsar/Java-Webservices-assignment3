package com.ecutb.web.services;

import com.ecutb.web.entities.User;
import com.ecutb.web.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<User> findById(String username){
        var found = userRepository.findById(username);

        if (found.isPresent()){
           return ResponseEntity.ok(found.get());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any users by username %s", username));
        }

    }

    public ResponseEntity<User> save(User user){
        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<User> update(User user){
        var found = findById(user.getUsername());
        if (found.getStatusCode().is2xxSuccessful()){
            return ResponseEntity.ok(userRepository.save(user));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not update user %s", user.getUsername()));
    }



}
