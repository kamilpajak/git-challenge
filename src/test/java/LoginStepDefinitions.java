import cucumber.api.java8.En;

public class LoginStepDefinitions implements En {

    public LoginStepDefinitions() {
        Given("^I am on login page$", () -> {
        });
        When("^I try to log in with \"([^\"]*)\" and \"([^\"]*)\"$", (String username, String password) -> {
        });
        Then("^error message pops up$", () -> {
        });
    }
}
