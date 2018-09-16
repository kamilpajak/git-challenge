package user_interface.page;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage() {
        open("https://github.com/login");
    }

    public LoginPage setLogin(String login) {
        $("#login_field").setValue(login);
        return this;
    }

    public LoginPage setPassword(String password) {
        $("#password").setValue(password);
        return this;
    }

    public LandingPage clickOnSignInButton() {
        $("[type=submit]").click();
        return page(LandingPage.class);
    }
}
