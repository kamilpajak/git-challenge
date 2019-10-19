package user_interface;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

abstract class Base {

    public GitHubMainPage logout() {
        SelenideElement userButton = $(by("aria-label", "View profile and more"));
        userButton.click();
        SelenideElement signOut = $(".logout-form button");
        signOut.click();
        return page(GitHubMainPage.class);
    }
}
