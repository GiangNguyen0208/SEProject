package com.se_project.be.controller;

import com.se_project.be.dto.request.OllamaFormMessageRequestDTo;
import com.se_project.be.dto.request.UserQuestionRequestDTO;
import com.se_project.be.dto.response.SendFomatMessageResponseDTO;
import com.se_project.be.service.AiService;
import com.se_project.be.service.UserQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OllamaController {

    @Autowired
    UserQuestionService userQuestionService;

    @Autowired
    AiService aiService;
    @PostMapping("/generate")
    public ResponseEntity<SendFomatMessageResponseDTO> askAi(@RequestBody OllamaFormMessageRequestDTo request) {
        // Gửi request đến Ollama API
        SendFomatMessageResponseDTO aiResponse = aiService.sendToAiModel(request);

        SendFomatMessageResponseDTO responseDTO = new SendFomatMessageResponseDTO(
                request.getModel(),   // Model AI được sử dụng
                LocalDate.now(),      // Ngày tạo response
                aiResponse.getResponse(),           // Kết quả từ AI
                true                  // Trạng thái success
        );

        return ResponseEntity.ok(responseDTO);
    }



}
