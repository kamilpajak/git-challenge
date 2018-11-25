package user_interface;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SettingsPage extends Repository {

    public SettingsPage clickOnDeleteRepository() {
        $(".Box--danger .Box-row", 3).$(".btn").click();
        return this;
    }

    public LandingPage confirmDelete(String password) {
        $(".Box--danger .Box-row", 3).$("input[name=verify]").setValue($("[itemprop=name] a").getText());
        $(".Box--danger .Box-row", 3).$("button[type=submit]").shouldBe(Condition.enabled).click();
        if ($("#sudo_password").exists()) {
            $("#sudo_password").setValue(password).pressEnter();
        }
        return page(LandingPage.class);
    }

    @Override
    public void isDisplayed() {
        $(".reponav .selected").shouldHave(Condition.text("Settings"));
    }
}
