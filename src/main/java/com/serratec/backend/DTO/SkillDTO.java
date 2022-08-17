package com.serratec.backend.DTO;

import com.serratec.backend.model.Skill;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {

    private String name;
    private String version;
    private String description;
    private String imageURL;

    public SkillDTO(Skill skill) {
        this.name = skill.getName();
        this.version = skill.getVersion();
        this.description = skill.getDescription();
        this.imageURL = skill.getImageURL();
    }
}
