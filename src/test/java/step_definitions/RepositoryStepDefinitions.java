package step_definitions;

import cucumber.api.java8.En;
import setup.Settings;
import user_interface.*;

public class RepositoryStepDefinitions implements En {

    private static final String USERNAME = Settings.properties.getProperty("github.username");
    private static final String PASSWORD = Settings.properties.getProperty("github.password");

    static GitHubMainPage gitHubMainPage;

    static LoginPage loginPage;

    static LandingPage landingPage;

    static RepositoryPage repositoryPage;

    static CreateNewRepositoryPage createNewRepositoryPage;

    static SettingsPage settingsPage;

    public RepositoryStepDefinitions() {
        Given("^I am logged in$", () -> {
            gitHubMainPage = new GitHubMainPage();
            loginPage = gitHubMainPage.clickOnSignIn();
            loginPage.setUsername(USERNAME).setPassword(PASSWORD).clickOnSignInButton();
            landingPage = new LandingPage();
        });
        When("^I create a \"([^\"]*)\" repository$", (String name) -> {
            createNewRepositoryPage = landingPage.clickOnNewRepository();
            repositoryPage = createNewRepositoryPage.setRepositoryName(name)
                    .setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    .withReadme(true)
                    .submit();
        });
        When("^I delete a \"([^\"]*)\" repository$", (String name) -> {
            settingsPage = repositoryPage.settings();
            landingPage = settingsPage.clickOnDeleteRepository().confirmDelete(PASSWORD);
        });
    }
}
