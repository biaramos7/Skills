package com.serratec.backend.repository;

import com.serratec.backend.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
}
