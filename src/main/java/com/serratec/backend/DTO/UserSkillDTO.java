package com.serratec.backend.DTO;

import java.time.LocalDate;

public interface UserSkillDTO {

    public Integer getId();
    public String getName();
    public String getVersion();
    public String getDescription();
    public String getImageURL();
    public String getKnowledgeLevel();
    public LocalDate getCreatedAt();
    public LocalDate getUpdatedAt();
}
