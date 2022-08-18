package com.serratec.backend.repository;

import com.serratec.backend.DTO.UserSkillDTO;
import com.serratec.backend.model.User;
import com.serratec.backend.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserSkillRepository extends JpaRepository<UserSkill, Integer> {
    @Query(value="select s.id as id, s.name as name ," +
            " s.version as version, s.description as description," +
            " s.imageURL as imageURL, us.knowledgeLevel as knowledgeLevel," +
            " us.createdAt as createdAt, us.updatedAt as updatedAt   " +
            "from UserSkill us join us.skill s where us.user=:user")
    List<UserSkillDTO> findByUser(User user);
}
