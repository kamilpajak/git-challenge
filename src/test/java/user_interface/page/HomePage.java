package user_interface.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    private SelenideElement newRepositoryButton = $("#your_repos a[href=\"/new\"]");

    public RepositoryCreationPage clickOnNewRepositoryButton() {
        this.newRepositoryButton.click();
        return page(RepositoryCreationPage.class);
    }
}
