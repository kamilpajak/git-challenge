package user_interface.page_objects;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class GitHubMainPage implements PageObjectModel {

    public GitHubMainPage() {
        isDisplayed();
    }

    @Override
    public void isDisplayed() {
        $(byText("Sign in")).should(Condition.appear);
    }
}
