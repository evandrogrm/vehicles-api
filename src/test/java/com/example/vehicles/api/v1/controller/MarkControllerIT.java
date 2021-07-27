package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.dto.MarkRequestDTO;
import com.example.vehicles.api.v1.dto.MarkResponseDTO;
import com.example.vehicles.api.v1.dto.MarkUpdateRequestDTO;
import io.restassured.response.Response;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class MarkControllerIT extends BaseControllerV1IT {

    public static final String RESOURCE = "marks/";

    @Test
    public void shouldForbiddenWhenAnonymous() {
        Response response = given().get(RESOURCE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void shouldCreateMarkProperly() {
        //Given
        MarkRequestDTO inputDTO = new MarkRequestDTO()
                .setName("Fiat2");

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        MarkResponseDTO responseDTO = (MarkResponseDTO) getResponseDTO(response, MarkResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getName()).isEqualTo(inputDTO.getName());
    }

    @Test
    public void shouldUpdateMarkProperly() {
        //Given
        MarkUpdateRequestDTO inputDTO = new MarkUpdateRequestDTO()
                .setId("e8f790b1-07b1-4bf3-b2d2-d21036c94130")
                .setName("Fiat2");

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        MarkResponseDTO responseDTO = (MarkResponseDTO) getResponseDTO(response, MarkResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo(inputDTO.getId());
        assertThat(responseDTO.getName()).isEqualTo(inputDTO.getName());
    }

    @Test
    public void shouldFindMarkProperly() {
        //Given
        String createdId = "e8f790b1-07b1-4bf3-b2d2-d21036c94130";
        String createdName = "Fiat";

        //When
        Response response = whenUser()
                .get(RESOURCE + createdId);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        MarkResponseDTO responseDTO = (MarkResponseDTO) getResponseDTO(response, MarkResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo(createdId);
        assertThat(responseDTO.getName()).isEqualTo(createdName);
    }

    @Test
    public void shouldFindMarkByName() throws IOException {
        //Given
        String createdId = "e8f790b1-07b1-4bf3-b2d2-d21036c94130";
        String createdName = "Fiat";

        //When
        Response response = whenUser()
                .param("name", "iAt")
                .get(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        List<MarkResponseDTO> responseDTO = getResponsePageDTO(response, MarkResponseDTO.class);
        MarkResponseDTO responseItemDTO = responseDTO.get(0);
        assertThat(responseItemDTO.getId()).isEqualTo(createdId);
        assertThat(responseItemDTO.getName()).isEqualTo(createdName);
    }

//    DEPENDENCY OF VEHICLES, CAN NOT DELETE WITH FK
//    @Test
//    public void shouldDeleteMarkProperly() {
//        //Given
//        String createdId = "e8f790b1-07b1-4bf3-b2d2-d21036c94130";
//
//        //When
//        Response response = whenUser()
//                .delete(RESOURCE + createdId);
//
//        //Then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
//    }
}
