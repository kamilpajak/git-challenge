package user_interface.page_objects;

import com.codeborne.selenide.Selenide;
import setup.Environment;

import static com.codeborne.selenide.Selenide.$;
import static setup.Settings.getProperty;
import static setup.Settings.setUp;

public class LoginPage {

    public LoginPage() {
        setUp();
        switch (Environment.valueOf(getProperty("github.environment").toUpperCase())) {
            case PRODUCTION:
                Selenide.open(Environment.PRODUCTION.url());
                break;
        }
    }

    public LoginPage setLogin(String login) {
        $("#login_field").setValue(login);
        return this;
    }

    public LoginPage setPassword(String password) {
        $("#password").setValue(password);
        return this;
    }

    public void clickOnSignInButton() {
        $("[type=submit]").click();
    }
}
