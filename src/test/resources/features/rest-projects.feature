Feature: REST - Redmine Rest Testing - Projects
  As a user ...
  I want to ...

  @rest
  Scenario: Create a project - JSON
    Given System is ready to send requests
    When System send a request to create project service
      | name            | RedmineProject                |
      | identifier      | redmineproject                |
      | description     | Redmine Project from cucumber |
      | inherit_members | false                         |
      | is_public       | true                          |
    Then The response status should be 201


  @rest
  Scenario Outline: Create a project from examples
    Given System is ready to send requests
    When System send a request to create project service from table:
      | name   | identifier   | description   | inherit_members   | is_public   |
      | <name> | <identifier> | <description> | <inherit_members> | <is_public> |
    Then  The response status should be 201

    Examples: Happy Paths
      | name            | identifier      | description                    | inherit_members | is_public |
      | RedmineProject1 | redmineproject1 | Redmine Project from cucumber1 | false           | true      |
      | RedmineProject2 | redmineproject2 | Redmine Project from cucumber2 | false           | true      |
      | RedmineProject3 | redmineproject3 | Redmine Project from cucumber3 | false           | true      |

  @rest
  Scenario: Get project by id - JSON
    Given System is ready to send requests
    When System send a request to get projects by id service
      | id | 285 |
    Then The response status should be 200
    And The schema is "project_schema.json"
    And System should responds with response data project
      | id   | 285                               |
      | name | Redmine Project Sw Testing Dragon |