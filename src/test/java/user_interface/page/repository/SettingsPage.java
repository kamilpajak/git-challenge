package user_interface.page.repository;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import user_interface.page.HomePage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SettingsPage extends RepositoryBasePage {

    private SelenideElement deleteRepositoryRow = $(".Box--danger .Box-row", 3);

    public SettingsPage clickOnDeleteRepository() {
        deleteRepositoryRow.$(".btn").click();
        return this;
    }

    public HomePage confirmDelete(String password) {
        deleteRepositoryRow.$("input[name=verify]").setValue(getNavigationBar().getRepositoryName());
        deleteRepositoryRow.$("button[type=submit]").shouldBe(Condition.enabled).click();
        if ($("#sudo_password").exists()) {
            $("#sudo_password").setValue(password).pressEnter();
        }
        return page(HomePage.class);
    }
}
