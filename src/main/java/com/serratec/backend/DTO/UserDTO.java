package com.serratec.backend.DTO;

import com.serratec.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String login;
    private LocalDate lastLoginDate;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.lastLoginDate = user.getLastLoginDate();
    }
}
