package com.serratec.backend.controller;

import com.serratec.backend.exception.SkillException;
import com.serratec.backend.model.Skill;
import com.serratec.backend.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService service;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Skill skill) throws SkillException {
        return ResponseEntity.ok(service.create(skill));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }
}