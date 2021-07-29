package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.dto.UserRequestDTO;
import com.example.vehicles.api.v1.dto.UserResponseDTO;
import com.example.vehicles.api.v1.dto.UserUpdateRequestDTO;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UserControllerIT extends BaseControllerV1IT {

    public static final String RESOURCE = "users/";

    @Test
    public void shouldForbiddenWhenAnonymous() {
        Response response = given().get(RESOURCE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void shouldCreateUserProperly() {
        //Given
        UserRequestDTO inputDTO = new UserRequestDTO()
                .setName("Userr")
                .setEmail("valid@email.com");

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        UserResponseDTO responseDTO = (UserResponseDTO) getResponseDTO(response, UserResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getName()).isEqualTo(inputDTO.getName());
        assertThat(responseDTO.getEmail()).isEqualTo(inputDTO.getEmail());
    }

    @Test
    public void shouldUpdateUserProperly() {
        //Given
        UserUpdateRequestDTO inputDTO = new UserUpdateRequestDTO()
                .setId("0cee6037-1d9f-435a-9e6b-ff0e90b90b17")
                .setName("User3Updated")
                .setEmail("valid@email.com");

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        UserResponseDTO responseDTO = (UserResponseDTO) getResponseDTO(response, UserResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo(inputDTO.getId());
        assertThat(responseDTO.getName()).isEqualTo(inputDTO.getName());
        assertThat(responseDTO.getEmail()).isEqualTo(inputDTO.getEmail());
    }

    @Test
    public void shouldFindUserProperly() {
        //Given
        String createdId = "0cee6037-1d9f-435a-9e6b-ff0e90b90b17";
        String createdName = "user3";
        String createdEmail = "user3@domain.com";

        //When
        Response response = whenUser()
                .get(RESOURCE + createdId);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        UserResponseDTO responseDTO = (UserResponseDTO) getResponseDTO(response, UserResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo(createdId);
        assertThat(responseDTO.getName()).isEqualTo(createdName);
        assertThat(responseDTO.getEmail()).isEqualTo(createdEmail);
    }

    @Test
    public void shouldFindUserByName() throws IOException {
        //Given
        String createdId = "0cee6037-1d9f-435a-9e6b-ff0e90b90b17";
        String createdName = "user3";
        String createdEmail = "user3@domain.com";

        //When
        Response response = whenUser()
                .param("name", "er3")
                .get(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        List<UserResponseDTO> responseDTO = getResponsePageDTO(response, UserResponseDTO.class);
        UserResponseDTO responseItemDTO = responseDTO.get(0);
        assertThat(responseItemDTO.getId()).isEqualTo(createdId);
        assertThat(responseItemDTO.getName()).isEqualTo(createdName);
        assertThat(responseItemDTO.getEmail()).isEqualTo(createdEmail);
    }

    @Test
    public void shouldDeleteUserProperly() {
        //Given
        String createdId = "0cee6037-1d9f-435a-9e6b-ff0e90b90b17";

        //When
        Response response = whenUser()
                .delete(RESOURCE + createdId);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
