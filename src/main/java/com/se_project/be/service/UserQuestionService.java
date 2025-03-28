package com.se_project.be.service;

import com.se_project.be.dto.request.UserQuestionRequestDTO;
import com.se_project.be.entity.UserQuestion;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserQuestionService {
    UserQuestion saveQuestion(int userId, String question);
    List<UserQuestion> getUserQuestions(int userId);
    UserQuestion saveFormattedQuestion(UserQuestionRequestDTO request);
}
