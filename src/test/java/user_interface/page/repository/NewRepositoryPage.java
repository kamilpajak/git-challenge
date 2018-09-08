package user_interface.page.repository;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NewRepositoryPage extends RepositoryBasePage {

    private SelenideElement repositoryNameField = $("#repository_name");

    private SelenideElement repositoryDescriptionField = $("#repository_description");

    private SelenideElement initializeWithReadmeCheckbox = $("#repository_auto_init");

    private SelenideElement createRepositoryButton = $("#new_repository button[type=submit]");

    public NewRepositoryPage enterName(String name) {
        repositoryNameField.setValue(name).shouldHave(Condition.cssClass("is-autocheck-successful"));
        return this;
    }

    public NewRepositoryPage enterDescription(String description) {
        repositoryDescriptionField.setValue(description);
        return this;
    }

    public NewRepositoryPage addReadme(boolean value) {
        initializeWithReadmeCheckbox.setSelected(value);
        return this;
    }

    public MainPage clickOnCreateRepositoryButton() {
        createRepositoryButton.shouldBe(Condition.enabled).click();
        return page(MainPage.class);
    }
}
