package user_interface;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

abstract class Base implements PageObjectModel {

    public GitHubMainPage logout() {
        SelenideElement userButton = $("#user-links li", 2);
        userButton.click();
        SelenideElement signOut = userButton.$(byText("Sign out"));
        signOut.click();
        return page(GitHubMainPage.class);
    }

    public CreateNewRepositoryPage clickOnNewRepository() {
        SelenideElement plusButton = $("#user-links li", 1);
        plusButton.click();
        SelenideElement newRepository = plusButton.$(byText("New repository"));
        newRepository.click();
        return page(CreateNewRepositoryPage.class);
    }
}
