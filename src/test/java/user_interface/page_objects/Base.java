package user_interface.page_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

abstract class Base implements PageObjectModel {

    public GitHubMainPage logout() {
        SelenideElement user = $("#user-links li", 2);
        user.click();
        SelenideElement signOut = user.$$("li").last();
        signOut.click();
        return page(GitHubMainPage.class);
    }
}
