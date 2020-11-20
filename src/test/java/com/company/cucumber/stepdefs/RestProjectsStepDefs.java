package com.company.cucumber.stepdefs;

import com.company.entities.Entity;
import com.company.entities.Project;
import com.company.util.RedmineEndpoints;
import io.cucumber.java.en.When;

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
}
