package com.se_project.be.service.serviceImpl;

import com.se_project.be.dao.UserDAO;
import com.se_project.be.dao.UserQuestionDAO;
import com.se_project.be.dto.request.UserQuestionRequestDTO;
import com.se_project.be.entity.UserQuestion;
import com.se_project.be.service.AiService;
import com.se_project.be.entity.User;
import com.se_project.be.service.UserQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserQuestionServiceImpl implements UserQuestionService {
    private final UserQuestionDAO userQuestionDAO;
    private final AiService aiService;
    private UserDAO userDAO;

    public UserQuestionServiceImpl(UserQuestionDAO userQuestionRepository, AiService aiService) {
        this.userQuestionDAO = userQuestionRepository;
        this.aiService = aiService;
    }

    @Transactional
    public UserQuestion saveQuestion(int userId, String question) {
        // Định dạng câu hỏi
        String formattedQuestion = "Câu hỏi được định dạng: " + question;

        // Lưu vào database
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setId(userId);
        userQuestion.setQuestion(question);
        userQuestion.setFormattedQuestion(formattedQuestion);
        userQuestion.setCreatedAt(LocalDateTime.now());

        return userQuestionDAO.save(userQuestion);
    }

    public List<UserQuestion> getUserQuestions(int userId) {
        return userQuestionDAO.findById(userId);
    }

    @Override
    public UserQuestion saveFormattedQuestion( UserQuestionRequestDTO request) {
        // Định dạng câu hỏi dựa trên input của người dùng
        Optional<User> user = userDAO.findById(request.getId());

        String formattedQuestion = formatQuestion(request);

        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setUser(user.get());
        userQuestion.setQuestion(request.toString());
        userQuestion.setFormattedQuestion(formattedQuestion);
        userQuestion.setCreatedAt(LocalDateTime.now());

        return userQuestionDAO.save(userQuestion);
    }

    private String formatQuestion(UserQuestionRequestDTO request) {
        return String.format(
                "Tôi hiện đang làm công việc %s. Trình độ của tôi hiện tại là %s. " +
                        "Mục tiêu của tôi là %s. %s. Hãy ta một lộ trình chi tiết Về việc học Tiếng Anh thông qua yêu cầu trên.",
                request.getYourJob(),
                request.getLevelNow(),
                request.getTarget(),
                (request.getMoreInfo() != null && !request.getMoreInfo().isEmpty())
                        ? "Thông tin thêm: " + request.getMoreInfo()
                        : ""
        );
    }
}
