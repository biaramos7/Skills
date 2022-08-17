package com.serratec.backend.controller;

import com.serratec.backend.DTO.UserDTO;
import com.serratec.backend.exception.UserException;
import com.serratec.backend.model.User;
import com.serratec.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody User user) throws UserException {
        return ResponseEntity.ok(service.create(user));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
