package com.company.features;

import com.company.config.RedmineConfig;
import com.company.entities.ListProjects;
import com.company.entities.Project;
import com.company.util.RedmineEndpoints;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void extractAllProjects(){

        Response response =
                given().
                        when()
                        .get(RedmineEndpoints.REDMINE_PROJECTS_JSON).
                        then()
                        .extract().response();


        List<Object> projects = response.path("projects");


        for (Object project :projects ){
            //System.out.println("Id Project: "+ (HashMap<Object, Object>)project.get("name"));
            System.out.println("");
        }
    }

    @Test
    public void extractAllProjectsNoPublic(){
        Response response =
                given().
                        when()
                        .get(RedmineEndpoints.REDMINE_PROJECTS_JSON).
                        then()
                        .extract().response();

        ListProjects listProjects = response.as(ListProjects.class);

        /*List<Project> projectList = listProjects.getProjects();

        for (Project project :projectList ){
            System.out.println("Id Project: "+ project.getId());
            System.out.println("Name Project: "+ project.getName());
            System.out.println("");
        }*/

        List<Project> projectList = listProjects.getProjects().parallelStream()
                .filter(project -> project.getIs_public().equals(false))
                .collect(Collectors.toList());

        projectList.forEach(
                project -> System.out.println(project.toString())
        );
    }

    @Test
    public void testCreateANewIssueByFileJSONWithValuesParameterized(){

        //1. Colocar el archivo .json en nuestros resources
        //2. Parametrizar los campos a reemplazar
        //2.- Leer el archivo y transaformarlo a un String
        //3.- Remplazar el valor de los campos con la data de prueba
        //4.- Realizar la petición

        String jsonBody = "{}";
        File jsonDataInFile = new File("src/main/resources/body_requests/issue.json");

        try {
             jsonBody = FileUtils.readFileToString(jsonDataInFile, StandardCharsets.UTF_8);

             jsonBody = jsonBody.replace("[PROJECT_ID]","1")
                     .replace("[SUBJECT]", "Este es un subject parametrizado")
                     .replace("[PRIORITY_ID]","1");

        } catch (IOException e) {
            e.printStackTrace();
        }

        given()
                .body(jsonBody).
                when()
                .post(RedmineEndpoints.REDMINE_ISSUES_JSON).
                then()
                .statusCode(201);
    }

    @Test
    public void testCreateANewIssueByFileJSON(){

        File jsonDataInFile = new File("src/main/resources/body_requests/issue_2.json");

        given()
                .body(jsonDataInFile).
        when()
                .post(RedmineEndpoints.REDMINE_ISSUES_JSON).
        then()
                .statusCode(201);
    }



}
