package com.serratec.backend.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_skill")
public class UserSkill {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer userId; //fk

    @NotNull
    private Integer skillId; //fk

    @NotNull
    private Integer knowledgeLevel; //de 1 a 10

    @NotNull
    private LocalDate createdAt;

    private LocalDate updatedAt;
}
