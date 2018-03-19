package user_interface.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryOpenPullRequestPage extends RepositoryBasePage {

    private SelenideElement baseSelector = $(".range-cross-repo-pair .commitish-suggester", 0);
    private SelenideElement compareSelector = $(".range-cross-repo-pair .commitish-suggester", 1);

    public RepositoryOpenPullRequestPage setBase(String name) {
        this.baseSelector.click();
        this.baseSelector.$("button").shouldHave(Condition.attribute("aria-expanded", "true"));
        this.baseSelector.$("input").setValue(name);
        this.baseSelector.$(".navigation-focus").shouldHave(Condition.text(name)).click();
        this.baseSelector.$("span[title^=base]").shouldHave(Condition.attribute("title", String.format("base: %s", name)));
        return this;
    }

    public RepositoryOpenPullRequestPage setCompare(String name) {
        this.compareSelector.click();
        this.compareSelector.$("button").shouldHave(Condition.attribute("aria-expanded", "true"));
        this.compareSelector.$("input").setValue(name);
        this.compareSelector.$(".navigation-focus").shouldHave(Condition.text(name)).click();
        this.compareSelector.$("span[title^=compare]").shouldHave(Condition.attribute("title", String.format("compare: %s", name)));
        return this;
    }

    public RepositoryOpenPullRequestPage setTitle(String title) {
        $("#pull_request_title").setValue(title);
        return this;
    }

    public RepositoryPullRequestPage clickOnCreatePullRequest() {
        $("#new_pull_request button[type=submit]").click();
        return page(RepositoryPullRequestPage.class);
    }
}
