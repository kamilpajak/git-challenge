package user_interface;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class NewRepositoryPage {

    private SelenideElement repositoryNameField = $("#repository_name");

    private SelenideElement repositoryDescriptionField = $("#repository_description");

    private SelenideElement initializeWithReadmeCheckbox = $("#repository_auto_init");

    public NewRepositoryPage enterName(String name) {
        this.repositoryNameField.setValue(name).shouldHave(Condition.cssClass("is-autocheck-successful"));
        return this;
    }
}
