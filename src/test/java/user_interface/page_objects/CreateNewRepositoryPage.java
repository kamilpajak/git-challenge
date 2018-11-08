package user_interface.page_objects;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateNewRepositoryPage extends Base {

    public CreateNewRepositoryPage setRepositoryName(String name) {
        $("#repository_name").setValue(name).shouldHave(Condition.cssClass("is-autocheck-successful"));
        return this;
    }

    public CreateNewRepositoryPage setDescription(String description) {
        $("#repository_description").setValue(description);
        return this;
    }

    public CreateNewRepositoryPage withReadme(boolean value) {
        $("#repository_auto_init").setSelected(value);
        return this;
    }

    public RepositoryPage submit() {
        $("#new_repository button[type=submit]").shouldBe(Condition.enabled).click();
        return page(RepositoryPage.class);
    }

    @Override
    public void isDisplayed() {
        assertThat(url(), is("https://github.com/new"));
    }
}
