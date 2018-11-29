package user_interface;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

abstract class Base {

    public GitHubMainPage logout() {
        SelenideElement userButton = $("#user-links li", 2);
        userButton.click();
        SelenideElement signOut = userButton.$(byText("Sign out"));
        signOut.click();
        return page(GitHubMainPage.class);
    }
}
