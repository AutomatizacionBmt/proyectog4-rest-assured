Feature: Rest - Redmine Testing - Issues
  As a user ...
  I want to...

  @rest
  Scenario: Get issues by list - JSON
    Given System is ready to send requests
    When System sends a request to list issues service
    Then The response status should be 200

  @rest
  Scenario: Get issues by id - JSON
    Given System is ready to send requests
    When System send a request to get issues service by id
      | id | 840 |
    Then The response status should be 200
    And System should responds with response data
      | id          | 840                                                   |
      | subject     | I cannot create a user.                               |
      | description | As an admin user, I cannot create an user when xml... |
      | start_date  | 2020-08-15                                            |

