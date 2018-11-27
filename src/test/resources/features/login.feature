Feature: GitHub user logs into application
  As a GitHub user,
  I want to log in to application,
  So that I can perform actions.

  Background: User navigates to login page
    When I enter GitHub address
    Then I click on Sign In button

  Scenario Outline: User tries to log in application with incorrect credentials
    When I try to log in with "<USERNAME>" and "<PASSWORD>"
    Then error message pops up

    Examples:
      | USERNAME      | PASSWORD           |
      | !@#$%^&       | test               |
      | test@test.com | incorrect_password |

  Scenario: User logs in and logs out
    Given I log in
    When I am on landing page
    Then I log out
