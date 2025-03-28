package com.se_project.be.resource;

import com.se_project.be.dao.UserDAO;
import com.se_project.be.dto.request.UserLoginRequestDTO;
import com.se_project.be.dto.request.UserQuestionRequestDTO;
import com.se_project.be.dto.request.UserRequestDTO;
import com.se_project.be.dto.response.CommonApiResponse;
import com.se_project.be.entity.User;
import com.se_project.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserResource {
    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    public ResponseEntity<CommonApiResponse> register(UserRequestDTO request) {
        CommonApiResponse response = new CommonApiResponse();

        User user  = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        userService.addUser(user);

        response.setMessage("User registered Successfully");
        response.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }
    public ResponseEntity<CommonApiResponse> login(UserLoginRequestDTO request) {
        CommonApiResponse response = new CommonApiResponse();

        User user = userDAO.findByEmail(request.getEmail());

        if (user == null) {
            response.setMessage("User was not register before. Pls register first !");
            response.setSuccess(false);
        } else {

            response.setMessage("User login Successfully");
            response.setSuccess(true);
        }
        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> receiveQuestionFromUser(UserQuestionRequestDTO request) {
        CommonApiResponse response = new CommonApiResponse();


        response.setMessage("Has received question");
        response.setSuccess(true);
        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

}
