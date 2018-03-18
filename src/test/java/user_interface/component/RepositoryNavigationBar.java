package user_interface.component;

import user_interface.page.RepositorySettingsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryNavigationBar {

    public String getRepositoryName() {
        return $(".public strong[itemprop=name]").getText();
    }

    public RepositorySettingsPage clickOnSettings() {
        $(".reponav-item", 6).click();
        return page(RepositorySettingsPage.class);
    }

}
