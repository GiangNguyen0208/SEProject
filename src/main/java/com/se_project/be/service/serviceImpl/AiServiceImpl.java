package com.se_project.be.service.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.se_project.be.dto.request.OllamaFormMessageRequestDTo;
import com.se_project.be.dto.response.SendFomatMessageResponseDTO;
import com.se_project.be.service.AiService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiServiceImpl implements AiService {
    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public AiServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
//    @Override
//    public String sendToAiModel(OllamaFormMessageRequestDTo question) {
//        String url = "http://localhost:11434/api/generate";
//
//        // Tạo JSON request body
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("model", "llama3.2:1b");
//        requestBody.put("prompt", question);
//        requestBody.put("stream", false);
//
//        // Thiết lập header
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");
//
//        // Gửi request
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//
//        return response.getBody();
//    }
@Override
public SendFomatMessageResponseDTO sendToAiModel(OllamaFormMessageRequestDTo requestDTO) {
    String url = "http://localhost:11434/api/generate";

    // Thiết lập header
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Gửi request với DTO
    HttpEntity<OllamaFormMessageRequestDTo> requestEntity = new HttpEntity<>(requestDTO, headers);
    try {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Parse JSON để lấy giá trị "response"
        JsonNode rootNode = objectMapper.readTree(responseEntity.getBody());
        String aiResponse = rootNode.path("response").asText(); // Lấy nội dung câu trả lời từ AI

        return new SendFomatMessageResponseDTO(
                requestDTO.getModel(),
                LocalDate.now(),
                aiResponse,  // Trả về nội dung thực tế của AI
                responseEntity.getStatusCode().is2xxSuccessful()
        );
    } catch (Exception e) {
        return new SendFomatMessageResponseDTO(
                requestDTO.getModel(),
                LocalDate.now(),
                "Lỗi khi gọi API Ollama: " + e.getMessage(),
                false
        );
    }
}
}
