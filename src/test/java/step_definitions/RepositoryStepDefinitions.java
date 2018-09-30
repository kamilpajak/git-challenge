package step_definitions;

import cucumber.api.java8.En;
import user_interface.page_objects.*;

import static setup.Settings.getProperty;

public class RepositoryStepDefinitions implements En {

    private final String username = getProperty("github.username");
    private final String password = getProperty("github.password");

    private GitHubMainPage gitHubMainPage;

    private LoginPage loginPage;

    private LandingPage landingPage;

    private RepositoryPage repositoryPage;

    private CreateNewRepositoryPage createNewRepositoryPage;

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
        When("^I create \"([^\"]*)\" repository$", (String name) -> {
            createNewRepositoryPage = landingPage.clickOnNewRepository();
            createNewRepositoryPage.isDisplayed();
            repositoryPage = createNewRepositoryPage.setRepositoryName(name)
                    .setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    .withReadme(true)
                    .submit();
        });
        Then("^I am on repository page$", () -> repositoryPage.isDisplayed());
        When("^I delete \"([^\"]*)\" repository$", (String name) -> {
            
        });
    }
}
