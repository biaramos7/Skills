package com.serratec.backend.service;

import com.serratec.backend.exception.SkillException;
import com.serratec.backend.model.Skill;
import com.serratec.backend.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    SkillRepository repository;

    public String create(Skill skill) throws SkillException {
        repository.save(skill);
        return "Skill criada com sucesso";
    }

    public String delete(Integer id) {
        repository.deleteById(id);
        return "Skill deletada com sucesso.";
    }
}
