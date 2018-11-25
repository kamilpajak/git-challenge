package user_interface;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class LandingPage extends Base {

    @Override
    public void isDisplayed() {
        $("#user-links").should(Condition.appear);
    }
}
