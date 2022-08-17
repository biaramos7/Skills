package com.serratec.backend.exception;

public class SkillException extends Exception {

    public SkillException() {
    }

    public SkillException(String message) {
        super(message);
    }

    public SkillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SkillException(Throwable cause) {
        super(cause);
    }
}