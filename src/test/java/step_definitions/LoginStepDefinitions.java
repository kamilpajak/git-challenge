package step_definitions;

import cucumber.api.java8.En;
import user_interface.page_objects.LandingPage;
import user_interface.page_objects.LoginPage;

import static setup.Settings.getProperty;

public class LoginStepDefinitions implements En {

    private LoginPage loginPage;

    private LandingPage landingPage;

    public LoginStepDefinitions() {
        Given("^I am on login page$", () -> loginPage = new LoginPage());
        When("^I try to log in with \"([^\"]*)\" and \"([^\"]*)\"$", (String username, String password)
                -> loginPage.setUsername(username).setPassword(password).clickOnSignInButton());
        Then("^error message pops up$", () -> loginPage.errorMessagePopsUp());
        When("^I log in$", () -> {
            String username = getProperty("github.username");
            String password = getProperty("github.password");
            loginPage.setUsername(username).setPassword(password).clickOnSignInButton();
        });
        Then("^I am on landing page$", () -> {
            landingPage = new LandingPage();
            landingPage.isDisplayed();
        });
    }
}
