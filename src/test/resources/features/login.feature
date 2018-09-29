Feature: GitHub user logs into application
  As a GitHub user,
  I want to log in to application,
  So that I can perform actions.

  Scenario Outline: User tries to log in application with incorrect credentials
    Given I am on login page
    When I try to log in with "<USERNAME>" and "<PASSWORD>"
    Then error message pops up

    Examples:
      | USERNAME      | PASSWORD           |
      | !@#$%^&       | test               |
      | test@test.com | incorrect_password |

  Scenario: User logs in
    Given I am on login page
    When I log in
    Then I am on landing page

  Scenario: User logs out
    Given I am logged in
    When I log out
    Then I am on GitHub main page
