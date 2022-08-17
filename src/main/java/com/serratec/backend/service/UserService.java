package com.serratec.backend.service;

import com.serratec.backend.exception.UserException;
import com.serratec.backend.model.User;
import com.serratec.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String delete(Integer id) {
        repository.deleteById(id);
        return "Usuario deletado com sucesso.";
    }
}
