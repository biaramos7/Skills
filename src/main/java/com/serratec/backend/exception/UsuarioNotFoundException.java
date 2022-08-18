package com.serratec.backend.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioNotFoundException extends UsernameNotFoundException {
    public UsuarioNotFoundException(String msg) {
        super(msg);
    }

    public UsuarioNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
