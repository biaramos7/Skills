package com.serratec.backend.DTO;

import com.serratec.backend.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDTO {

    private Integer id;
    private String name;
    private String version;
    private String description;
    private String imageURL;

    public SkillDTO(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.version = skill.getVersion();
        this.description = skill.getDescription();
        this.imageURL = skill.getImageURL();
    }
}
