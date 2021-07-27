package com.example.vehicles.api.v1.controller;

import com.example.vehicles.api.v1.service.TokenService;
import com.example.vehicles.domain.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = CoreControllerIT.Configuration.class, initializers = CoreControllerIT.Initializer.class)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:mysql_data/beforeTestRun.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:mysql_data/afterTestRun.sql")
})
public abstract class CoreControllerIT {

    protected static ObjectMapper objectMapper = new ObjectMapper();

    private static String mysqlImage = "mysql:5.7";

    public static GenericContainer<?> mysqlContainer = new MySQLContainer(mysqlImage)
            .withUsername("user")
            .withPassword("user123")
            .withDatabaseName("vehicles-database")
            .withConfigurationOverride("mysql_conf_override");

    static {
        mysqlContainer.start();
    }

    @LocalServerPort
    int randomPort;

    @Before
    public void init() {
        RestAssured.port = randomPort;
    }

    @TestConfiguration
    public static class Configuration {
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            List<String> pairs = new LinkedList<>();

            String jdbcURL = ("jdbc:mysql://" + mysqlContainer.getContainerIpAddress()
                    + ":" + mysqlContainer.getMappedPort(MySQLContainer.MYSQL_PORT)
                    + "/vehicles-database");

            pairs.add("spring.datasource.jdbcUrl:" + jdbcURL);
            pairs.add("spring.datasource.url:" + jdbcURL);
            pairs.add("ro.spring.datasource.jdbcUrl:" + jdbcURL);
            pairs.add("ro.spring.datasource.url:" + jdbcURL);

            TestPropertyValues.of(pairs).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    protected RequestSpecification whenUser() {
        return whenToken(buildFakeUserToken());
    }

    private RequestSpecification whenToken(String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token);
    }

    @Autowired
    private TokenService tokenService;

    protected String LOGGED_USER_ID = "0cee6037-1d9f-435a-9e6b-ff0e90b90b15";

    protected String buildFakeUserToken() {
        User user = new User()
                .setId(LOGGED_USER_ID)
                .setName("name")
                .setEmail("abc@acv.com")
                .setCreatedAt(new Date())
                .setUpdatedAt(new Date());
        return tokenService.generateTokenUser(user);
    }

    protected static Object getResponseDTO(Response response, Class<?> clazz) {
        return response.body().jsonPath().getObject("$", clazz);
    }

    @SuppressWarnings("unchecked")
    protected static <T> List<T> getResponsePageDTO(Response response, Class<T> clazz) throws IOException {
        Map<String, Object> objectMap = objectMapper.readValue(response.getBody().print(), Map.class);
        List<Object> content = (List<Object>) objectMap.get("content");
        List<T> result = new ArrayList<>();
        for (Object o : content) {
            result.add(objectMapper.convertValue(o, clazz));
        }
        return result;
    }
}
