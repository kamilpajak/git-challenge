Feature: GitHub user makes changes on repositories
  As a GitHub user,
  I want to make changes on repositories,
  So that I can create and delete them.

  Scenario: User creates new repository
    Given I am logged in
    When I create "lorem-ipsum-dolor" repository
    Then I am on repository page

  Scenario: User deletes repository
    Given I am logged in
    When I delete "lorem-ipsum-dolor" repository
    Then I am on landing page