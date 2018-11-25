package step_definitions;

import cucumber.api.java8.En;
import user_interface.GitHubMainPage;
import user_interface.LandingPage;
import user_interface.LoginPage;

import static setup.Settings.properties;

public class LoginStepDefinitions implements En {

    private final String username = properties.getProperty("github.username");
    private final String password = properties.getProperty("github.password");

    private LoginPage loginPage;

    private LandingPage landingPage;

    private GitHubMainPage gitHubMainPage;

    public LoginStepDefinitions() {
        Given("^I enter GitHub address$", () -> gitHubMainPage = GitHubMainPage.open());
        Then("^I am on GitHub main page$", () -> gitHubMainPage.isDisplayed());
        When("^I click on Sign In button$", () -> loginPage = gitHubMainPage.clickOnSignIn());
        Given("^I am on login page$", () -> loginPage.isDisplayed());
        When("^I try to log in with \"([^\"]*)\" and \"([^\"]*)\"$", (String username, String password)
                -> loginPage.setUsername(username).setPassword(password).clickOnSignInButton());
        Then("^error message pops up$", () -> loginPage.errorMessagePopsUp());
        When("^I log in$", () -> loginPage.setUsername(username).setPassword(password).clickOnSignInButton());
        Then("^I am on landing page$", () -> {
            landingPage = new LandingPage();
            landingPage.isDisplayed();
        });
        When("^I log out$", () -> gitHubMainPage = landingPage.logout());
    }
}
