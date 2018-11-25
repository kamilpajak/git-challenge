package user_interface;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import setup.Environment;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static setup.Settings.properties;
import static setup.Settings.setUp;

public class GitHubMainPage implements PageObjectModel {

    private GitHubMainPage() {
    }

    public static GitHubMainPage open() {
        setUp();
        Environment environment = Environment.valueOf(properties.getProperty("github.environment").toUpperCase());
        switch (environment) {
            case PRODUCTION:
                Selenide.open(Environment.PRODUCTION.url());
                break;
        }
        return page(GitHubMainPage.class);
    }

    public LoginPage clickOnSignIn() {
        $(byText("Sign in")).click();
        return page(LoginPage.class);
    }

    @Override
    public void isDisplayed() {
        $(byText("Sign in")).should(Condition.appear);
    }
}
