package com.se_project.be.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class UserInputRequestDTO {
    String userInput;

    public String getUserInput() {
        return userInput;
    }
}
