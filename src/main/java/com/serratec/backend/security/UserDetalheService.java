package com.serratec.backend.security;

import com.serratec.backend.exception.UsuarioNotFoundException;
import com.serratec.backend.model.User;
import com.serratec.backend.repository.UserRepository;
import com.serratec.backend.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetalheService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetalhe loadUserByUsername(String username) throws UsuarioNotFoundException {
        System.out.println("Entra load by username  ");
        Optional<User> usuario = repository.findByLogin(username);
        if (usuario.isEmpty()) {
            throw new UsuarioNotFoundException("Usuário " + username + "não encontrado");
        }
        return new UserDetalhe(usuario.get());
    }
}
