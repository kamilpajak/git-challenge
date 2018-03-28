package user_interface.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import user_interface.page.repository.MainPage;
import user_interface.page.repository.NewRepositoryPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {

    private SelenideElement newRepositoryButton = $("#your_repos a[href=\"/new\"]");

    private SelenideElement repositorySearchField = $("#your-repos-filter");

    public NewRepositoryPage clickOnNewRepositoryButton() {
        this.newRepositoryButton.click();
        return page(NewRepositoryPage.class);
    }

    public MainPage selectRepository(String name) {
        this.repositorySearchField.setValue(name);
        $(String.format(".mini-repo-list-item [title$=\\/%s]", name)).should(Condition.exist).click();
        return page(MainPage.class);
    }

    public boolean repositoryExists(String name) {
        this.repositorySearchField.setValue(name);
        return $(String.format(".mini-repo-list-item [title$=\\/%s]", name)).exists();
    }
}
