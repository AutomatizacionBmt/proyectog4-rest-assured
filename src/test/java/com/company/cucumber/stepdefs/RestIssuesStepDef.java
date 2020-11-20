package com.company.cucumber.stepdefs;

import com.company.util.RedmineEndpoints;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestIssuesStepDef {

    private RequestSpecification request;
    private Response response;

    @Given("System is ready to send requests")
    public void systemIsReadyToSendRequests() {

                request = given();
    }

    @When("System sends a request to list issues service")
    public void systemSendsARequestToListIssuesService() {

                response = request.when()
                        .get(RedmineEndpoints.REDMINE_ISSUES_JSON);
    }

    @Then("The response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
                response.
                        then()
                        .statusCode(statusCode);
    }

    @When("System send a request to get issues service by id")
    public void systemSendARequestToGetIssuesServiceById(Map<String, String> issue) {

        response = request.
                pathParam("idIssue", issue.get("id")).
        when()
                .get(RedmineEndpoints.SINGLE_REDMINE_ISSUES_JSON);
    }

    @And("System should responds with response data")
    public void systemShouldRespondsWithResponseData(Map<String, String> expectedData) {

        JsonPath actualData = new JsonPath(response.getBody().asString());

        Assert.assertEquals("El id no es el correcto",
                Integer.parseInt(expectedData.get("id")),
                actualData.getInt("issue.id"));

        Assert.assertEquals("El subject no es correcto",
                expectedData.get("subject"),
                actualData.getString("issue.subject"));

        Assert.assertEquals("La descripcion no es correcta",
                expectedData.get("description"),
                actualData.getString("issue.description"));

        Assert.assertEquals("La fecha no es correcta",
                expectedData.get("start_date"),
                actualData.getString("issue.start_date"));
    }
}