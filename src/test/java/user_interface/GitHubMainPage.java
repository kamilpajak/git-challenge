package user_interface;

import setup.Environment;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static setup.Settings.properties;
import static setup.Settings.setUp;

public class GitHubMainPage {

    public GitHubMainPage() {
        setUp();
        Environment environment = Environment.valueOf(properties.getProperty("github.environment").toUpperCase());
        switch (environment) {
            case PRODUCTION:
                open(Environment.PRODUCTION.url());
                break;
        }
    }

    public LoginPage clickOnSignIn() {
        $(byText("Sign in")).click();
        return page(LoginPage.class);
    }
}
