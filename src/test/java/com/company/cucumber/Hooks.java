package com.company.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Hooks {

    public static RequestSpecification requestSpecification;

    public static ResponseSpecification responseSpecification;


    @Before
    public static void setUp() {

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8081")
                .setBasePath("/")
                //Los que utilizan una url sin puerto comentar esta línea
                .setPort(8081)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Redmine-API-Key", "54d992219a4d120df54d5a72cb152e8b89d20d65")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        responseSpecification = new ResponseSpecBuilder()
                //.expectResponseTime(lessThan(3000L))
                //.expectStatusCode(200)
                .build();


        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

    @After
    public static void cleanUp(){
        RestAssured.reset();
    }

}
