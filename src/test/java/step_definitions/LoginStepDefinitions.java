package step_definitions;

import cucumber.api.java8.En;
import setup.Settings;
import user_interface.GitHubMainPage;
import user_interface.LandingPage;
import user_interface.LoginPage;

public class LoginStepDefinitions implements En {

    static LoginPage loginPage;

    static LandingPage landingPage;

    static GitHubMainPage gitHubMainPage;

    public LoginStepDefinitions() {
        Given("^I enter GitHub address$", () -> gitHubMainPage = new GitHubMainPage());
        When("^I click on Sign In button$", () -> loginPage = gitHubMainPage.clickOnSignIn());
        When("^I try to log in with \"([^\"]*)\" and \"([^\"]*)\"$", (String username, String password)
                -> loginPage.setUsername(username).setPassword(password).clickOnSignInButton());
        Then("^error message pops up$", () -> loginPage.errorMessagePopsUp());
        When("^I log in$", () -> {
            String username = Settings.properties.getProperty("github.username");
            String password = Settings.properties.getProperty("github.password");
            loginPage.setUsername(username).setPassword(password).clickOnSignInButton();
        });
        Then("^I am on landing page$", () -> landingPage = new LandingPage());
        When("^I log out$", () -> gitHubMainPage = landingPage.logout());
    }
}
