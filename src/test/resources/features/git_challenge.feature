Feature: Git challenge

  Background: User logs in
    Given user opens browser with GitHub login page
    When user fills in Login with data from file
    And user fills in Password with data from file
    And user clicks on Sign In button
    Then user lands on landing page

  Scenario: User creates a repository
    When user clicks on New Repository button
    And user fills in Repository name with "lorem-ipsum-dolor"
    And user fills in Description with "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    And user checks Initialize this repository with README option
    And user clicks on Create Repository button
    Then user lands on "lorem-ipsum-dolor" repository page

  Scenario: User pushes a commit
    When user selects repository "lorem-ipsum-dolor"
    And user creates a new branch with name "develop"
    And user clicks on Create New File button
    And user fills in Filename with "Cras et massa.txt"
    And user commits new file with message "Cras et massa id metus aliquet rutrum."
    Then user sees "Cras et massa.txt" file on a list

  Scenario: User creates a pull request
    When user selects repository "lorem-ipsum-dolor"
    And user clicks on New Pull Request button
    And user sets Base as "master"
    And user sets Compare as "develop"
    And user fills in Title with "Cras eu hendrerit turpis"
    And user clicks on Create Pull Request button
    Then user lands on "Cras eu hendrerit turpis" pull request page

  Scenario: User accepts a pull request
    When user selects repository "lorem-ipsum-dolor"
    And user clicks on Pull Requests button
    And user selects pull request "Cras eu hendrerit turpis" from the list
    And user clicks on Merge Pull Request button
    Then user sees that pull request is merged

  Scenario: User deletes a repository
    When user selects repository "lorem-ipsum-dolor"
    And user clicks on Settings button
    And user clicks on Delete Repository button
    And user enters repository name to confirm delete
    Then user sees that repository "lorem-ipsum-dolor" does not exist

