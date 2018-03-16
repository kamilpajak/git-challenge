package user_interface;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public static HomePage login(String login, String password) {
        open("https://github.com/login");
        $("#login_field").setValue(login);
        $("#password").setValue(password).pressEnter();
        return page(HomePage.class);
    }
}
