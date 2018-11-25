package user_interface;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginPage implements PageObjectModel {

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

    @Override
    public void isDisplayed() {
        assertThat(url(), is("https://github.com/login"));
    }
}
