package user_interface.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    private SelenideElement newRepositoryButton = $("#your_repos a[href=\"/new\"]");

    private SelenideElement repositorySearchField = $("#your-repos-filter");

    public RepositoryCreationPage clickOnNewRepositoryButton() {
        this.newRepositoryButton.click();
        return page(RepositoryCreationPage.class);
    }

    public RepositoryPage selectRepository(String name) {
        this.repositorySearchField.setValue(name);
        $(String.format(".mini-repo-list-item [title$=\\/%s]", name)).click();
        return page(RepositoryPage.class);
    }
}
