package com.serratec.backend.controller;

import com.serratec.backend.DTO.UserDTO;
import com.serratec.backend.DTO.UserSkillDTO;
import com.serratec.backend.exception.UserException;
import com.serratec.backend.model.User;
import com.serratec.backend.model.UserSkill;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody User user) throws UserException {
        return ResponseEntity.ok(service.update(user, id));
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

    //Relação UserSkill

    @PostMapping("/associar-skill")
    public ResponseEntity<String> associarSkill(@RequestBody UserSkill userSkill) throws UserException {
        return ResponseEntity.ok(service.associarSkill(userSkill));
    }

    @PutMapping("/lista-skills/{id}")
    public ResponseEntity<List<UserSkillDTO>> listaSkills(@PathVariable Integer id) throws UserException {
        return ResponseEntity.ok(service.listaSkills(id));
    }

    @PutMapping("/update-level/{id}")
    public ResponseEntity<String> updateLevel(@PathVariable Integer id, @RequestBody UserSkill userSkill) throws UserException {
        return ResponseEntity.ok(service.updateLevel(userSkill, id));
    }

    @DeleteMapping("/delete-relacao/{id}")
    public ResponseEntity<String> deleteRelacao(@PathVariable Integer id) {
        return ResponseEntity.ok(service.deleteRelacao(id));
    }
}
