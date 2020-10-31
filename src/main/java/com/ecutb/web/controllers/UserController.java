package com.ecutb.web.controllers;


import com.ecutb.web.entities.User;
import com.ecutb.web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(){
        var result = userService.findAll();
        if (result.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any users."));
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<User> findUserById(@PathVariable String username){
        var result = userService.findById(username);
        if (result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find any users by username %s", username));
        }else return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user){
        var result = userService.update(user);
        if (result == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not update user %s", user.getUsername()));
        }else return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/(username}")
    public ResponseEntity<Boolean> delete(@PathVariable String username){
        var result = userService.delete(username);
        if(result) return ResponseEntity.ok(result);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not delete user %s", username));
    }
