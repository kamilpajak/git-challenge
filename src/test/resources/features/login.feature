Feature: GitHub user logs into application
  As a GitHub user,
  I want to log in to application,
  So that I can perform actions.

  Background: User navigates to login page
    Given I enter GitHub address
    When I am on GitHub main page
    Then I click on Sign In button

  Scenario Outline: User tries to log in application with incorrect credentials
    Given I am on login page
    When I try to log in with "<USERNAME>" and "<PASSWORD>"
    Then error message pops up

    Examples:
      | USERNAME      | PASSWORD           |
      | test@test.com | incorrect_password |

  Scenario: User logs in and logs out
    Given I am on login page
    When I log in
    And I am on landing page
    Then I log out
    And I am on GitHub main page
