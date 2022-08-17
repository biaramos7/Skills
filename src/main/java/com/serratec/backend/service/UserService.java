package com.serratec.backend.service;

import com.serratec.backend.DTO.UserDTO;
import com.serratec.backend.exception.UserException;
import com.serratec.backend.model.User;
import com.serratec.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    BCryptPasswordEncoder bCrypt;

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
}
