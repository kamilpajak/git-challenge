package user_interface.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryCreationPage extends RepositoryBasePage {

    private SelenideElement repositoryNameField = $("#repository_name");

    private SelenideElement repositoryDescriptionField = $("#repository_description");

    private SelenideElement initializeWithReadmeCheckbox = $("#repository_auto_init");

    private SelenideElement createRepositoryButton = $("#new_repository button[type=submit]");

    public RepositoryCreationPage enterName(String name) {
        this.repositoryNameField.setValue(name).shouldHave(Condition.cssClass("is-autocheck-successful"));
        return this;
    }

    public RepositoryCreationPage enterDescription(String description) {
        this.repositoryDescriptionField.setValue(description);
        return this;
    }

    public RepositoryCreationPage addReadme(boolean value) {
        this.initializeWithReadmeCheckbox.setSelected(value);
        return this;
    }

    public RepositoryPage clickOnCreateRepositoryButton() {
        this.createRepositoryButton.shouldBe(Condition.enabled).click();
        return page(RepositoryPage.class);
    }
}
