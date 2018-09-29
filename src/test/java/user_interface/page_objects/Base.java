package user_interface.page_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

abstract class Base implements PageObjectModel {

    public void logout() {
        SelenideElement user = $("#user-links li", 2);
        user.click();
        SelenideElement signOut = user.$$("li").last();
        signOut.click();
    }
}
