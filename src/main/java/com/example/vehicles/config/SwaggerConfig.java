package com.example.vehicles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
//@PropertySources(
//        @PropertySource("classpath:META-INF/api_v1_vehicles.properties")
//)
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

//    @Autowired
//    private Environment environment;
//
//    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
//            new HashSet<>(Arrays.asList("application/json",
//                    "application/xml"));
//
//    @Bean
//    public Docket apiV1() {
//        String version = "v1";
//        boolean enabled = Boolean.parseBoolean(environment.getProperty(version + "." + "swagger-ui.enable"));
//        System.out.println(">>>>>>> SWAGGER ENABLED: " + enabled);
//
////        ParameterBuilder aParameterBuilder = new ParameterBuilder();
////        aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header")
////                .description("Authorization Header").required(true).build();
////
////        List<Parameter> aParameters = new ArrayList<>();
////        aParameters.add(aParameterBuilder.build());
////        Docket docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any()).build().apiInfo(apiInfo()).globalOperationParameters(aParameters).produces(DEFAULT_PRODUCES_AND_CONSUMES);
//
//        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
//
//        docket.tags(new Tag("Users", "Users"));
//
//        docket.enable(enabled);
//        return docket;
//    }
//
//    @Bean
//    public UiConfiguration uiConfiguration() {
//        return UiConfigurationBuilder.builder()
//                .displayRequestDuration(true)
//                .validatorUrl("")
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        Contact contact = new Contact("Company name", "http://www.company.com", "support@company.com");
//        return new ApiInfo("Vehicles API", "A Vehicles challenge API", "1.0",
//                "https://www.company.com/termsandconditions", contact, "MIT",
//                "https://www.company.com/privacypolicy", new ArrayList<>());
//    }
}
