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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    @NotNull
    private Skill skill;

    @NotNull
    private Integer knowledgeLevel;

    @NotNull
    private LocalDate createdAt;

    private LocalDate updatedAt;
}
