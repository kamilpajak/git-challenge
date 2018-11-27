package user_interface;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public LoginPage setUsername(String login) {
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

    public void errorMessagePopsUp() {
        $(byText("Incorrect username or password.")).should(Condition.appear);
    }
}
