package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.dto.*;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class VehicleControllerIT extends BaseControllerV1IT {

    public static final String RESOURCE = "vehicles/";

    @Test
    public void shouldForbiddenWhenAnonymous() {
        Response response = given().get(RESOURCE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void shouldCreateVehicleWithExistingMark() {
        //Given
        String existingMarkId = "e8f790b1-07b1-4bf3-b2d2-d21036c94130";
        VehicleRequestDTO inputDTO = new VehicleRequestDTO()
                .setMarkName("Fiat")
                .setModel("Argo")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        VehicleResponseDTO responseDTO = (VehicleResponseDTO) getResponseDTO(response, VehicleResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getMark().getId()).isEqualTo(existingMarkId);
        assertThat(responseDTO.getMark().getName()).isEqualTo(inputDTO.getMarkName());
        assertThat(responseDTO.getModel()).isEqualTo(inputDTO.getModel());
        assertThat(responseDTO.getFabricationYear()).isEqualTo(inputDTO.getFabricationYear());
        assertThat(responseDTO.getColor()).isEqualTo(inputDTO.getColor());
        assertThat(responseDTO.getMileage()).isEqualTo(inputDTO.getMileage());
    }

    @Test
    public void shouldCreateVehicleWithNewMark() {
        //Given
        VehicleRequestDTO inputDTO = new VehicleRequestDTO()
                .setMarkName("Fiat3")
                .setModel("Argo")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        VehicleResponseDTO responseDTO = (VehicleResponseDTO) getResponseDTO(response, VehicleResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getMark().getId()).isNotBlank();
        assertThat(responseDTO.getMark().getName()).isEqualTo("Fiat3");
        assertThat(responseDTO.getModel()).isEqualTo(inputDTO.getModel());
        assertThat(responseDTO.getFabricationYear()).isEqualTo(inputDTO.getFabricationYear());
        assertThat(responseDTO.getColor()).isEqualTo(inputDTO.getColor());
        assertThat(responseDTO.getMileage()).isEqualTo(inputDTO.getMileage());
    }

    @Test
    public void shouldUpdateVehicleWithExistingMark() {
        //Given
        String existingMarkId = "e8f790b1-07b1-4bf3-b2d2-d21036c94130";
        VehicleUpdateRequestDTO inputDTO = new VehicleUpdateRequestDTO()
                .setId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setMarkName("Fiat")
                .setModel("Argo")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        VehicleResponseDTO responseDTO = (VehicleResponseDTO) getResponseDTO(response, VehicleResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getMark().getId()).isEqualTo(existingMarkId);
        assertThat(responseDTO.getMark().getName()).isEqualTo(inputDTO.getMarkName());
        assertThat(responseDTO.getModel()).isEqualTo(inputDTO.getModel());
        assertThat(responseDTO.getFabricationYear()).isEqualTo(inputDTO.getFabricationYear());
        assertThat(responseDTO.getColor()).isEqualTo(inputDTO.getColor());
        assertThat(responseDTO.getMileage()).isEqualTo(inputDTO.getMileage());
    }

    @Test
    public void shouldUpdateVehicleWithNewMark() {
        //Given
        VehicleUpdateRequestDTO inputDTO = new VehicleUpdateRequestDTO()
                .setId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setMarkName("Fiat3")
                .setModel("Argo")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        VehicleResponseDTO responseDTO = (VehicleResponseDTO) getResponseDTO(response, VehicleResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getMark().getId()).isNotBlank();
        assertThat(responseDTO.getMark().getName()).isEqualTo("Fiat3");
        assertThat(responseDTO.getModel()).isEqualTo(inputDTO.getModel());
        assertThat(responseDTO.getFabricationYear()).isEqualTo(inputDTO.getFabricationYear());
        assertThat(responseDTO.getColor()).isEqualTo(inputDTO.getColor());
        assertThat(responseDTO.getMileage()).isEqualTo(inputDTO.getMileage());
    }

    @Test
    public void shouldInvalidateWrongMarkNameOnCreate() {
        //Given
        VehicleRequestDTO inputDTO = new VehicleRequestDTO()
                .setMarkName("Fi")
                .setModel("Argo")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("\"rejectedValue\":\"Fi\"")).isTrue();
    }

    @Test
    public void shouldInvalidateWrongModelOnCreate() {
        //Given
        VehicleRequestDTO inputDTO = new VehicleRequestDTO()
                .setMarkName("Fia")
                .setModel("Ar")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("\"rejectedValue\":\"Ar\"")).isTrue();
    }

    @Test
    public void shouldInvalidateWrongFabricationYearOnCreate() {
        //Given
        VehicleRequestDTO inputDTO = new VehicleRequestDTO()
                .setMarkName("Fia")
                .setModel("Arg")
                .setFabricationYear(2050)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("\"rejectedValue\":2050")).isTrue();
    }

    @Test
    public void shouldInvalidateWrongMarkNameOnUpdate() {
        //Given
        VehicleUpdateRequestDTO inputDTO = new VehicleUpdateRequestDTO()
                .setId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setMarkName("Fi")
                .setModel("Argo")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("\"rejectedValue\":\"Fi\"")).isTrue();
    }

    @Test
    public void shouldInvalidateWrongModelOnUpdate() {
        //Given
        VehicleUpdateRequestDTO inputDTO = new VehicleUpdateRequestDTO()
                .setId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setMarkName("Fia")
                .setModel("Ar")
                .setFabricationYear(2021)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("\"rejectedValue\":\"Ar\"")).isTrue();
    }

    @Test
    public void shouldInvalidateWrongFabricationYearOnUpdate() {
        //Given
        VehicleUpdateRequestDTO inputDTO = new VehicleUpdateRequestDTO()
                .setId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setMarkName("Fia")
                .setModel("Arg")
                .setFabricationYear(2050)
                .setColor("Vermelho")
                .setMileage(13000);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .put(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("\"rejectedValue\":2050")).isTrue();
    }

    @Test
    public void shouldFindVehicleProperly() {
        //Given
        String createdId = "2de3c69e-dc5d-4404-9c37-7e2dbe20f381";

        //When
        Response response = whenUser()
                .get(RESOURCE + createdId);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        VehicleResponseDTO responseDTO = (VehicleResponseDTO) getResponseDTO(response, VehicleResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo("2de3c69e-dc5d-4404-9c37-7e2dbe20f381");
        assertThat(responseDTO.getMark().getId()).isEqualTo("e8f790b1-07b1-4bf3-b2d2-d21036c94131");
        assertThat(responseDTO.getMark().getName()).isEqualTo("Volkswagen");
        assertThat(responseDTO.getModel()).isEqualTo("Gol");
        assertThat(responseDTO.getFabricationYear()).isEqualTo(2010);
        assertThat(responseDTO.getColor()).isEqualTo("Cinza");
        assertThat(responseDTO.getMileage()).isEqualTo(80000);
    }

    @Test
    public void shouldFindVehicleByModel() throws IOException {
        //Given
        String createdName = "HB20";

        //When
        Response response = whenUser()
                .param("model", "b20")
                .get(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        List<VehicleResponseDTO> responseDTO = getResponsePageDTO(response, VehicleResponseDTO.class);
        VehicleResponseDTO responseItemDTO = responseDTO.get(0);
        assertThat(responseItemDTO.getId()).isEqualTo("2de3c69e-dc5d-4404-9c37-7e2dbe20f382");
        assertThat(responseItemDTO.getModel()).isEqualTo(createdName);
    }

//    DEPENDENCY OF RESTS, CAN NOT DELETE WITH FK
//    @Test
//    public void shouldDeleteVehicleProperly() {
//        //Given
//        String createdId = "2de3c69e-dc5d-4404-9c37-7e2dbe20f382";
//
//        //When
//        Response response = whenUser()
//                .delete(RESOURCE + createdId);
//
//        //Then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
//    }
}
