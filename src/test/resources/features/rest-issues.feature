Feature: REST - Redmine Testing - Issues
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
      | id | 3492 |
    Then The response status should be 200
    And System should responds with response data
      | id          | 3492                                 |
      | subject     | Issue modificado por JHHA            |
      | description | Esta es una descripcion paara prueba |
      | start_date  | 2021-02-14                           |

