package com.company.cucumber.stepdefs;

import com.company.entities.Entity;
import com.company.entities.Project;
import com.company.util.RedmineEndpoints;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.Map;
import java.util.Random;

public class RestProjectsStepDefs {

    private RestCommonStepDefs commonSteps;

    public RestProjectsStepDefs(RestCommonStepDefs commonSteps){
        this.commonSteps = commonSteps;
    }

    @When("System send a request to create project service")
    public void systemSendARequestToCreateProjectService(Map<String, String> projectData) {

        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName(projectData.get("name") + randomNumber);
        project.setIdentifier(projectData.get("identifier") + randomNumber);
        project.setDescription(projectData.get("description") + randomNumber);
        project.setInherit_members(Boolean.parseBoolean(projectData.get("inherit_members")));
        project.setIs_public(Boolean.parseBoolean(projectData.get("is_public")));

        Entity entity = new Entity(project);

        commonSteps.response = commonSteps.request
                .body(entity)
                .when()
                .post(RedmineEndpoints.REDMINE_PROJECTS_JSON);

    }

    @When("System send a request to create project service from table:")
    public void systemSendARequestToCreateProjectServiceFromTable(DataTable dataTable) {

        Integer randomNumber = (new Random()).nextInt(900000) + 100000;

        Project project = new Project();
        project.setName(dataTable.row(1).get(0) + randomNumber);
        project.setIdentifier(dataTable.row(1).get(1) + randomNumber);
        project.setDescription(dataTable.row(1).get(2) + randomNumber);
        project.setInherit_members(Boolean.parseBoolean(dataTable.row(1).get(3)));
        project.setIs_public(Boolean.parseBoolean(dataTable.row(1).get(4)));

        Entity entity = new Entity(project);

        commonSteps.response = commonSteps.request
                .body(entity)
                .when()
                .post(RedmineEndpoints.REDMINE_PROJECTS_JSON);

    }

    @When("System send a request to get projects by id service")
    public void systemSendARequestToGetProjectsByIdService(Map<String, String> project) {

        commonSteps.response = commonSteps.request.
                pathParam("idProject", project.get("id")).
                when()
                        .get(RedmineEndpoints.SINGLE_REDMINE_PROJECTS_JSON);

    }

    @And("System should responds with response data project")
    public void systemShouldRespondsWithResponseDataProject(Map<String, String> expectedData) {

        JsonPath actualData = new JsonPath(commonSteps.response.getBody().asString());

        Assert.assertEquals("El id no es el correcto", Integer.parseInt(expectedData.get("id")),
                actualData.getInt("project.id"));

        Assert.assertEquals("El nombre no es el correcto", expectedData.get("name"),
                actualData.getString("project.name"));

    }
}
