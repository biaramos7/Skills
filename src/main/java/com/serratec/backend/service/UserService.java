package com.serratec.backend.service;

import com.serratec.backend.DTO.UserDTO;
import com.serratec.backend.DTO.UserSkillDTO;
import com.serratec.backend.exception.UserException;
import com.serratec.backend.model.User;
import com.serratec.backend.model.UserSkill;
import com.serratec.backend.repository.UserRepository;
import com.serratec.backend.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    BCryptPasswordEncoder bCrypt;

    @Autowired
    UserSkillRepository userSkillRepository;

    public void verificaExiste(User user) throws UserException {
        Optional<User> optional = repository.findByLogin(user.getLogin());
        if (optional.isPresent()) {
            throw new UserException("Usuário já existe.");
        }
    }

    public String create(User user) throws UserException {
        verificaExiste(user);
        user.setPassword(bCrypt.encode(user.getPassword()));
        repository.save(user);
        return "Usuário criado com sucesso";
    }

    public List<UserDTO> findAll() {
        List<User> listaUser = repository.findAll();
        List<UserDTO> listaUserDTO = new ArrayList<>();
        for (User user : listaUser) {
            UserDTO userDTO = new UserDTO(user);
            listaUserDTO.add(userDTO);
        }
        return listaUserDTO;
    }

    public String update(User user, Integer id) throws UserException {
        Optional<User> optional = repository.findById(id);
        System.out.println("usuario: " + optional);
        if (optional.isEmpty()) {
            throw new UserException("O Id informado não foi encontrado.");
        }
        boolean atualiza = false;
        User oldUser = optional.get();
        if (user.getLogin() != null && !oldUser.getLogin().equals(user.getLogin())) {
            oldUser.setLogin(user.getLogin());
            atualiza = true;
        }
        if (user.getPassword() != null && !oldUser.getPassword().equals(bCrypt.encode(user.getPassword()))) {
            oldUser.setPassword(bCrypt.encode(user.getPassword()));
            atualiza = true;
        }
        if (atualiza) {
            repository.save(oldUser);
        }
        return "Usuario atualizado com sucesso.";
    }

    public String delete(Integer id) {
        repository.deleteById(id);
        return "Usuario deletado com sucesso.";
    }

    //Relação User Skill

    public String associarSkill(UserSkill userSkill) throws UserException {
        UserSkill user = new UserSkill();
        user.setUser(userSkill.getUser());
        user.setSkill(userSkill.getSkill());
        if (userSkill.getKnowledgeLevel() > 0 && userSkill.getKnowledgeLevel() < 10) {
            user.setKnowledgeLevel(userSkill.getKnowledgeLevel());
        } else {
            throw new UserException("KnowledgeLevel deve ser entre 1 e 10");
        }
        user.setCreatedAt(LocalDate.now());
        userSkillRepository.save(user);
        return "Skill associada com sucesso";
    }

    public List<UserSkillDTO> listaSkills(Integer id) {
        Optional<User> optional = repository.findById(id);
        List<UserSkillDTO> listUserSkill = userSkillRepository.findByUser(optional.get());
        return listUserSkill;
    }

    public String updateLevel(UserSkill userSkill, Integer id) throws UserException {
        if (userSkill.getKnowledgeLevel() > 0 && userSkill.getKnowledgeLevel() < 10) {
            Optional<UserSkill> optional = userSkillRepository.findById(id);
            UserSkill oldUserSkill = optional.get();
            oldUserSkill.setKnowledgeLevel(userSkill.getKnowledgeLevel());
            oldUserSkill.setUpdatedAt(LocalDate.now());
            userSkillRepository.save(oldUserSkill);
            return "Level da Skill atualizado com sucesso";
        } else {
            throw new UserException("KnowledgeLevel deve ser entre 1 e 10");
        }
    }

    public String deleteRelacao(Integer id) {
        userSkillRepository.deleteById(id);
        return "Relação user_skill deletada com sucesso.";
    }

    public void updateLastLogin(String username) {
        Optional<User> optional = repository.findByLogin(username);
        if (optional.isPresent()) {
            User user = optional.get();
            user.setLastLoginDate(LocalDate.now());
            repository.save(user);
        }
    }
}
