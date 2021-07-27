package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.dto.RentRequestDTO;
import com.example.vehicles.api.v1.dto.RentResponseDTO;
import io.restassured.response.Response;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class RentControllerIT extends BaseControllerV1IT {

    public static final String RESOURCE = "rents/";

    @Test
    public void shouldForbiddenWhenAnonymous() {
        Response response = given().get(RESOURCE);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void shouldRentVehicleProperly() {
        //Given
        DateTime startDT = new DateTime(2021, 9, 1, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 9, 2, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        RentResponseDTO responseDTO = (RentResponseDTO) getResponseDTO(response, RentResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getVehicle().getId()).isEqualTo("2de3c69e-dc5d-4404-9c37-7e2dbe20f381");
        assertThat(responseDTO.getUser().getId()).isEqualTo(LOGGED_USER_ID);
        assertThat(responseDTO.getRentStartAt()).isEqualTo(start);
        assertThat(responseDTO.getRentEndAt()).isEqualTo(end);
    }

    @Test
    public void shouldRentVehicleForOtherUserProperly() {
        //Given
        DateTime startDT = new DateTime(2021, 9, 1, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 9, 2, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setUserId("0cee6037-1d9f-435a-9e6b-ff0e90b90b16")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        RentResponseDTO responseDTO = (RentResponseDTO) getResponseDTO(response, RentResponseDTO.class);
        assertThat(responseDTO.getId()).isNotBlank();
        assertThat(responseDTO.getVehicle().getId()).isEqualTo("2de3c69e-dc5d-4404-9c37-7e2dbe20f381");
        assertThat(responseDTO.getUser().getId()).isEqualTo("0cee6037-1d9f-435a-9e6b-ff0e90b90b16");
        assertThat(responseDTO.getRentStartAt()).isEqualTo(start);
        assertThat(responseDTO.getRentEndAt()).isEqualTo(end);
    }

    @Test
    public void shouldInvalidateStartDateBeforeToday() {
        //Given
        DateTime startDT = new DateTime(2021, 7, 20, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 7, 21, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("rent.beforeNow")).isTrue();
    }

    @Test
    public void shouldInvalidateEndDateBeforeStartDate() {
        //Given
        DateTime startDT = new DateTime(2021, 8, 22, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 8, 21, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("rent.endBeforeStart")).isTrue();
    }

    @Test
    public void shouldInvalidateDaysDifferenceTooLong() {
        //Given
        DateTime startDT = new DateTime(2021, 8, 20, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 12, 20, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("rent.daysDifferenceGreaterThanMaximum")).isTrue();
    }

    @Test
    public void shouldInvalidateVehicleAlreadyRent() {
        //Given
        DateTime startDT = new DateTime(2021, 8, 2, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 8, 3, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("rent.vehicleAlreadyRent")).isTrue();
    }

    @Test
    public void shouldInvalidateUserAlreadyRentOnPeriod() {
        //Given
        DateTime startDT = new DateTime(2021, 8, 4, 0, 0); //DateTime(year, month, day, hour, minute, second)
        Date start = startDT.toDate();

        DateTime endDT = new DateTime(2021, 8, 5, 0, 0);
        Date end = endDT.toDate();

        RentRequestDTO inputDTO = new RentRequestDTO()
                .setVehicleId("2de3c69e-dc5d-4404-9c37-7e2dbe20f381")
                .setRentStartAt(start)
                .setRentEndAt(end);

        //When
        Response response = whenUser()
                .body(inputDTO)
                .post(RESOURCE);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().print().contains("rent.userAlreadyRent")).isTrue();
    }

    @Test
    public void shouldFindRentProperly() {
        //Given
        String createdId = "15d81443-88ae-4f59-a1cd-020bc9edb47a";
        DateTime startDT = DateTime.parse("2021-08-02");
        Date start = startDT.toDate();
        DateTime endDT = DateTime.parse("2021-08-03");
        Date end = endDT.toDate();

        //When
        Response response = whenUser()
                .get(RESOURCE + createdId);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK.value());

        RentResponseDTO responseDTO = (RentResponseDTO) getResponseDTO(response, RentResponseDTO.class);
        assertThat(responseDTO.getId()).isEqualTo(createdId);
        assertThat(responseDTO.getVehicle().getId()).isEqualTo("2de3c69e-dc5d-4404-9c37-7e2dbe20f381");
        assertThat(responseDTO.getUser().getId()).isEqualTo("0cee6037-1d9f-435a-9e6b-ff0e90b90b16");
        assertThat(responseDTO.getRentStartAt()).isEqualTo(start);
        assertThat(responseDTO.getRentEndAt()).isEqualTo(end);
    }

    @Test
    public void shouldDeleteRentProperly() {
        //Given
        String createdId = "15d81443-88ae-4f59-a1cd-020bc9edb47a";

        //When
        Response response = whenUser()
                .delete(RESOURCE + createdId);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
