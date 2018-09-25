package step_definitions;

import cucumber.api.java8.En;
import user_interface.page_objects.LoginPage;

public class LoginStepDefinitions implements En {

    private LoginPage loginPage;

    public LoginStepDefinitions() {
        Given("^I am on login page$", () -> loginPage = new LoginPage());
        When("^I try to log in with \"([^\"]*)\" and \"([^\"]*)\"$", (String username, String password) -> loginPage.setUsername(username).setPassword(password).clickOnSignInButton());
        Then("^error message pops up$", () -> loginPage.errorMessagePopsUp());
    }
}
