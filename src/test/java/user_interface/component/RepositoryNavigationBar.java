package user_interface.component;

import user_interface.page.repository.PullRequestListPage;
import user_interface.page.repository.SettingsPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryNavigationBar {

    public String getRepositoryName() {
        return $(".public strong[itemprop=name]").getText();
    }

    public SettingsPage clickOnSettings() {
        $(".reponav-item", 6).click();
        return page(SettingsPage.class);
    }

    public PullRequestListPage clickOnPullRequests() {
        $(".reponav-item", 2).click();
        return page(PullRequestListPage.class);
    }
}
