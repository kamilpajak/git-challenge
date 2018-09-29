package user_interface.page_objects;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class LandingPage extends Base {

    public LandingPage() {
        isDisplayed();
    }

    @Override
    public void isDisplayed() {
        $("#user-links").should(Condition.appear);
    }
}
