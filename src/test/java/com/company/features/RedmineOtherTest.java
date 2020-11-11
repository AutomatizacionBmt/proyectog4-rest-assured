package com.company.features;

import com.company.config.RedmineConfig;
import com.company.util.RedmineEndpoints;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RedmineOtherTest extends RedmineConfig {

    @Test
    public void testValidateResponseDataProject(){

        given()
                .pathParam("idProject", 459).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_PROJECTS_JSON).
        then()
                .statusCode(200)
                .body("project.id", equalTo(459))
                .body("project.name", equalTo("Redmine Project Postman"))
                .body("project.identifier", equalTo("redmineproject998"))
                .body("project.description", equalTo("Una description"));
    }

    @Test
    public void testValidateResponseDataSecondProject(){

        given().

        when()
                .get(RedmineEndpoints.REDMINE_PROJECTS_JSON).
        then()
                .statusCode(200)
                .body("projects[1].id", equalTo(269))
                .body("projects[1].name", equalTo("CatherineTesting"))
                .body("projects[1].identifier", equalTo("catherinetesting"))
                .body("projects[1].description", equalTo(""));
    }

    @Test
    public void testValidateResponseAnyProject(){

        given().

                when()
                .get(RedmineEndpoints.REDMINE_PROJECTS_JSON).
                then()
                .statusCode(200)
                .body("projects.id", hasItems(359,16));
    }

    @Test
    public void extractAllProjectsIds(){

        Response response =
                        given().
                        when()
                                .get(RedmineEndpoints.REDMINE_PROJECTS_JSON).
                        then()
                                .extract().response();


        List<Integer> idProjects = response.path("projects.id");

        for (Integer id :idProjects ){
            System.out.println("Id Project: "+id);
        }

    }
}
