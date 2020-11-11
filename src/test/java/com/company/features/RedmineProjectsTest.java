package com.company.features;

import com.company.config.RedmineConfig;
import com.company.entities.Entity;
import com.company.entities.Project;
import com.company.util.RedmineEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RedmineProjectsTest extends RedmineConfig {


    @Test
    public void testProjectSerialization(){

        Project project = new Project();
        project.setName("RedmineProject");
        project.setIdentifier("redmineproject997");
        project.setDescription("Esta es una descripción");
        project.setInherit_members(false);
        project.setIs_public(true);

        Entity entity = new Entity(project);

        given()
                .body(entity).
        when()
                .post(RedmineEndpoints.REDMINE_PROJECTS_JSON).
        then()
                .statusCode(201);
    }

}
