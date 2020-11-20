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