package step_definitions;

import cucumber.api.java8.En;
import user_interface.page_objects.GitHubMainPage;
import user_interface.page_objects.LandingPage;
import user_interface.page_objects.LoginPage;

import static setup.Settings.getProperty;

public class LoginStepDefinitions implements En {

    private final String username = getProperty("github.username");
    private final String password = getProperty("github.password");

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
        Given("^I am logged in$", () -> {
            gitHubMainPage = GitHubMainPage.open();
            gitHubMainPage.isDisplayed();
            loginPage = gitHubMainPage.clickOnSignIn();
            loginPage.isDisplayed();
            loginPage.setUsername(username).setPassword(password).clickOnSignInButton();
            landingPage = new LandingPage();
            landingPage.isDisplayed();
        });
        When("^I log out$", () -> gitHubMainPage = landingPage.logout());
    }
}
