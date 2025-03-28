package com.se_project.be.service;

import com.se_project.be.dto.request.OllamaFormMessageRequestDTo;
import com.se_project.be.dto.response.SendFomatMessageResponseDTO;

public interface AiService {
    SendFomatMessageResponseDTO sendToAiModel(OllamaFormMessageRequestDTo request);
}
