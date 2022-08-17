package com.serratec.backend.service;

import com.serratec.backend.DTO.SkillDTO;
import com.serratec.backend.DTO.UserDTO;
import com.serratec.backend.exception.SkillException;
import com.serratec.backend.exception.UserException;
import com.serratec.backend.model.Skill;
import com.serratec.backend.model.User;
import com.serratec.backend.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    SkillRepository repository;

    public String create(Skill skill) throws SkillException {
        repository.save(skill);
        return "Skill criada com sucesso";
    }

    public List<SkillDTO> findAll() {
        List<Skill> listaSkill = repository.findAll();
        List<SkillDTO> listaSkillDTO = new ArrayList<>();
        for (Skill skill : listaSkill) {
            SkillDTO skillDTO = new SkillDTO(skill);
            listaSkillDTO.add(skillDTO);
        }
        return listaSkillDTO;
    }

    public String update(Skill skill, Integer id) throws SkillException {
        Optional<Skill> optional = repository.findById(id);
        System.out.println("ID -> " + id);
        System.out.println("Skill -> " + optional);
        if (optional.isEmpty()) {
            throw new SkillException("O Id informado não foi encontrado.");
        }
        boolean atualiza = false;
        Skill oldSkill = optional.get();
        if(skill.getName() != null && !oldSkill.getName().equals(skill.getName())){
            oldSkill.setName(skill.getName());
            atualiza = true;
        }
        if(skill.getVersion() != null && !oldSkill.getVersion().equals(skill.getVersion())){
            oldSkill.setVersion(skill.getVersion());
            atualiza = true;
        }
        if(skill.getDescription() != null && !oldSkill.getDescription().equals(skill.getDescription())){
            oldSkill.setDescription(skill.getDescription());
            atualiza = true;
        }
        if(skill.getImageURL() != null && !oldSkill.getImageURL().equals(skill.getImageURL())){
            oldSkill.setImageURL(skill.getImageURL());
            atualiza = true;
        }
        if (atualiza) {
            repository.save(oldSkill);
        }
        return "Skill atualizada com sucesso.";
    }

    public String delete(Integer id) {
        repository.deleteById(id);
        return "Skill deletada com sucesso.";
    }
}
