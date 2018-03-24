Feature: Git challenge

  Background: User logs in
    Given user opens browser with GitHub login page
    When user fills in Login with data from file
    And user fills in Password with data from file
    And user clicks on Sign In Button
    Then user lands on home page

  Scenario: User creates a repository
    When user clicks on New Repository Button
    And user fills in Repository name with "lorem-ipsum-dolor"
    And user fills in Description with "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    And user clicks on Create Repository Button
    Then user lands on "lorem-ipsum-dolor" repository page


