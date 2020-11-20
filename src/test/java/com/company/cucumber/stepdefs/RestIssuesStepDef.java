package com.company.cucumber.stepdefs;

import com.company.util.RedmineEndpoints;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
}