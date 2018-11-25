package step_definitions;

import cucumber.api.java8.En;
import user_interface.*;

import static setup.Settings.properties;

public class RepositoryStepDefinitions implements En {

    private final String username = properties.getProperty("github.username");
    private final String password = properties.getProperty("github.password");

    private GitHubMainPage gitHubMainPage;

    private LoginPage loginPage;

    private LandingPage landingPage;

    private RepositoryPage repositoryPage;

    private CreateNewRepositoryPage createNewRepositoryPage;

    private SettingsPage settingsPage;

    public RepositoryStepDefinitions() {
        Given("^I am logged in$", () -> {
            gitHubMainPage = GitHubMainPage.open();
            gitHubMainPage.isDisplayed();
            loginPage = gitHubMainPage.clickOnSignIn();
            loginPage.isDisplayed();
            loginPage.setUsername(username).setPassword(password).clickOnSignInButton();
            landingPage = new LandingPage();
            landingPage.isDisplayed();
        });
        When("^I create a \"([^\"]*)\" repository$", (String name) -> {
            createNewRepositoryPage = landingPage.clickOnNewRepository();
            createNewRepositoryPage.isDisplayed();
            repositoryPage = createNewRepositoryPage.setRepositoryName(name)
                    .setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    .withReadme(true)
                    .submit();
        });
        Then("^I am on repository page$", () -> repositoryPage.isDisplayed());
        When("^I delete a \"([^\"]*)\" repository$", (String name) -> {
            settingsPage = repositoryPage.settings();
            settingsPage.isDisplayed();
            landingPage = settingsPage.clickOnDeleteRepository().confirmDelete(password);
        });
    }
}
