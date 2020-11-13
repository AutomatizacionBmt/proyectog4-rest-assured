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
import static org.hamcrest.Matchers.*;

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
        //3.- Leer el archivo
        //4.- Transformar el archivo  a un String
        //5.- Remplazar el valor de los campos con la data de prueba
        //6.- Realizar la petición

        String jsonBody = "{}";
        //3
        File jsonDataInFile = new File("src/main/resources/body_requests/issue.json");

        try {
             //4
             jsonBody = FileUtils.readFileToString(jsonDataInFile, StandardCharsets.UTF_8);

             //5
             jsonBody = jsonBody.replace("[PROJECT_ID]","1")
                     .replace("[SUBJECT]", "Este es un subject parametrizado")
                     .replace("[PRIORITY_ID]","1");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //6
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

    @Test
    public void testUploadFileRedmine(){

        File image = new File("src/main/resources/bdd.png");

        Response response =
                given().
                        //multiPart("file", image)
                        //.multiPart("", image).
                        contentType("application/octet-stream")
                        .body(image).
                when()
                        .post("/uploads.json?filename=bdd.png").
                then()
                        .statusCode(201)
                        .extract().response();

        String tokenFile = response.path("upload.token");

        String issueWithFile = "{\n" +
                "  \"issue\": {\n" +
                "    \"project_id\": 1,\n" +
                "    \"subject\": \"Issue creado por JH desde Postman\",\n" +
                "    \"priority_id\": 4,\n" +
                "    \"uploads\":[\n" +
                "        {\n" +
                "            \"token\": \""+tokenFile+"\",\n" +
                "            \"filename\": \"bdd.png\",\n" +
                "            \"description\": \"Una description\",\n" +
                "            \"content_type\": \"image/png\"\n" +
                "        }\n" +
                "    ]\n" +
                "  }\n" +
                "}";


        given()
                .body(issueWithFile).
                when()
                .post(RedmineEndpoints.REDMINE_ISSUES_JSON).
                then()
                .statusCode(201);

    }

    @Test
    public void getSingleIssueJSONAndValidateIfExistsFields(){

        given()
                .pathParam("idIssue", 69).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUES_JSON).
        then()
                .statusCode(200)
                .body("$", hasKey("issue"))
                .body("issue", hasKey("id"))
                .body("issue", hasKey("project"))
                .body("issue", hasKey("subject"))
                .body("issue.priority", hasKey("name"));
    }
}
