package user_interface.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public LoginPage setLogin(String login) {
        $("#login_field").setValue(login);
        return this;
    }

    public LoginPage setPassword(String password) {
        $("#password").setValue(password);
        return this;
    }

    public HomePage clickOnSignInButton() {
        $("[type=submit]").click();
        return page(HomePage.class);
    }
}
