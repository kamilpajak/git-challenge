package user_interface.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import user_interface.page.repository.MainPage;
import user_interface.page.repository.NewRepositoryPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LandingPage {

    private SelenideElement newRepositoryButton = $(".js-repos-container a[href=\"/new\"]");

    private SelenideElement repositorySearchField = $("#dashboard-repos-filter");

    public NewRepositoryPage clickOnNewRepositoryButton() {
        newRepositoryButton.click();
        return page(NewRepositoryPage.class);
    }

    public MainPage selectRepository(String name) {
        repositorySearchField.setValue(name);
        $(String.format("[data-filterable-for=dashboard-repos-filter] [title=%s]", name)).should(Condition.exist).click();
        return page(MainPage.class);
    }

    public boolean repositoryExists(String name) {
        repositorySearchField.setValue(name);
        return $(String.format("[data-filterable-for=dashboard-repos-filter] [title=%s]", name)).exists();
    }
}
