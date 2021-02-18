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
                .setBaseUri("http://198.211.98.120:8081")
                .setBasePath("/")
                //Los que utilizan una url sin puerto comentar esta línea
                .setPort(8081)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Redmine-API-Key", "89edaa063143731973b213d759c6945ef07bbb03")
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
