package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.dto.*;
import com.example.vehicles.api.v1.repository.UserRepositoryV1;
import com.example.vehicles.domain.User;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginControllerIT extends BaseControllerV1IT {

    public static final String RESOURCE = "login";

    @Autowired
    private UserRepositoryV1 userRepositoryV1;

    @Test
    public void shouldCreateUserOnLogin() {
        //Given
        LoginRequestDTO inputDTO = new LoginRequestDTO()
                .setName("Userr")
                .setEmail("valid@email.com");

        //When
        Response response = given()
                .header("Content-Type", "application/json")
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        LoginResponseDTO responseDTO = (LoginResponseDTO) getResponseDTO(response, LoginResponseDTO.class);
        assertThat(responseDTO.getId()).isNotEmpty();
        assertThat(responseDTO.getName()).isEqualTo(inputDTO.getName());
        assertThat(responseDTO.getEmail()).isEqualTo(inputDTO.getEmail());
        assertThat(responseDTO.getToken()).isNotEmpty();

        User user = userRepositoryV1.findById(responseDTO.getId()).get();
        assertThat(user.getName()).isEqualTo(inputDTO.getName());
        assertThat(user.getEmail()).isEqualTo(inputDTO.getEmail());
    }

    @Test
    public void shouldUpdateUserOnLoginWhenExistingEmail() {
        //Given
        LoginRequestDTO inputDTO = new LoginRequestDTO()
                .setName("Userr")
                .setEmail("user3@domain.com");

        //When
        Response response = given()
                .header("Content-Type", "application/json")
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        LoginResponseDTO responseDTO = (LoginResponseDTO) getResponseDTO(response, LoginResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo("0cee6037-1d9f-435a-9e6b-ff0e90b90b17");
        assertThat(responseDTO.getName()).isEqualTo(inputDTO.getName());
        assertThat(responseDTO.getEmail()).isEqualTo(inputDTO.getEmail());
        assertThat(responseDTO.getToken()).isNotEmpty();

        User user = userRepositoryV1.findById("0cee6037-1d9f-435a-9e6b-ff0e90b90b17").get();
        assertThat(user.getName()).isEqualTo(inputDTO.getName());
        assertThat(user.getEmail()).isEqualTo(inputDTO.getEmail());
    }
}
