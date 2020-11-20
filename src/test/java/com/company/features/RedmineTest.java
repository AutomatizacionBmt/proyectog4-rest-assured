package com.company.features;

import com.company.config.RedmineConfig;
import com.company.util.RedmineEndpoints;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RedmineTest extends RedmineConfig {

    @Test
    public void listarTodosLosIssuesJSON(){

        given().
        when()
                .get(RedmineEndpoints.REDMINE_ISSUES_JSON).
        then()
                //.log().all()
                .statusCode(200);
    }

    @Test
    public void crearUnNuevoIssueJSON(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "    \"project_id\": 1,\n" +
                "    \"subject\": \"Issue creado por JH desde RestAssured\",\n" +
                "    \"priority_id\": 4\n" +
                "  }\n" +
                "}";

        given()
                .body(issueBody).
        when()
                .post(RedmineEndpoints.REDMINE_ISSUES_JSON).
        then()
                .statusCode(201);
    }

    @Test
    public void borrarUnIssueJSON(){
        given().
        when()
            .delete("issues/2249.json").
        then()
                .statusCode(204);
    }

    @Test
    public void actualizarUnIssueJSON(){

        String issueBody = "{\n" +
                "  \"issue\": {\n" +
                "    \"subject\": \"Issue modificado por JHHA desde RestAssured\",\n" +
                "    \"priority_id\" : 5,\n" +
                "    \"notes\": \"The subject was changed\" \n" +
                "  }\n" +
                "}";


        given()
                .body(issueBody)
                .pathParam("idIssue", 2252).
        when()
                .put(RedmineEndpoints.SINGLE_REDMINE_ISSUES_JSON).
        then()
                .statusCode(204);
    }

    @Test
    public void obtenerUnIssueJSON(){
        given()
                .pathParam("idIssue",2253).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUES_JSON).
        then()
                .statusCode(200);

    }


}
