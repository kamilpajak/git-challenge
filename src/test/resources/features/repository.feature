Feature: GitHub user makes changes on repositories
  As a GitHub user,
  I want to make changes on repositories,
  So that I can create and delete them.

  Background: User logs in
    Given I am logged in

  Scenario: User creates and then deletes a repository
    Given I create a "lorem-ipsum-dolor" repository
    When I delete a "lorem-ipsum-dolor" repository
    Then I am on landing page
