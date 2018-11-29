package user_interface;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GitHubMainPage {

    public LoginPage clickOnSignIn() {
        $(byText("Sign in")).click();
        return page(LoginPage.class);
    }
}
