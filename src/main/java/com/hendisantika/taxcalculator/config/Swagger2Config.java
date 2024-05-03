package com.hendisantika.taxcalculator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : tax-calculator
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-07
 * Time: 21:46
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class Swagger2Config {

//    @Bean
//    public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.hendisantika.taxcalculator.controller"))
//                .build()
//                .apiInfo(getApiInfo());
//    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("tax-service")
                .pathsToMatch("/**")
                .build();
    }

//    private ApiInfo getApiInfo() {
//        return new ApiInfoBuilder()
//                .title("Simple Tax Calculator")
//                .description("\"Simple Tax Calculator for managing User Items Tax\"")
//                .version("1.0.0")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
//                .contact(new Contact("Hendi Santika", "https://www.twitter.com/hendisantika34", "hendisantika@yahoo.co.id"))
//                .build();
//    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application" +
            "-version}") String appVersion) {
        Contact contact = new Contact();
        contact.setEmail("hendisantika@yahoo.co.id");
        contact.setName("Hendi Santika");
        contact.setUrl("https://www.s.id/hendisantika");
        return new OpenAPI()
                .info(new Info()
                        .title("Simple Tax Calculator")
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                        .contact(contact)
                );

    }

}
