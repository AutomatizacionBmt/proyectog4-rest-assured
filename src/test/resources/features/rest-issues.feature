Feature: Rest - Redmine Testing - Issues
  As a user ...
  I want to...

  Scenario: Get issues by list - JSON
    Given System is ready to send requests
    When System sends a request to list issues service
    Then The response status should be 200

