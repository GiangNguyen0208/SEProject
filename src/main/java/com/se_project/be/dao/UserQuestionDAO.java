package com.se_project.be.dao;

import com.se_project.be.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQuestionDAO extends JpaRepository<UserQuestion, Long> {
    List<UserQuestion> findById(int userId); // Lấy danh sách câu hỏi theo userId
}
