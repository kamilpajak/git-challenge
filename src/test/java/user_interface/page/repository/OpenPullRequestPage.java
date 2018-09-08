package user_interface.page.repository;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class OpenPullRequestPage extends RepositoryBasePage {

    private SelenideElement baseSelector = $(".range-cross-repo-pair .commitish-suggester", 0);

    private SelenideElement compareSelector = $(".range-cross-repo-pair .commitish-suggester", 1);

    public OpenPullRequestPage setBase(String name) {
        String blankslateText = $(".blankslate").getText();
        baseSelector.click();
        baseSelector.$("button").shouldHave(Condition.attribute("aria-expanded", "true"));
        baseSelector.$("input").setValue(name);
        baseSelector.$(".navigation-focus").shouldHave(Condition.text(name)).click();
        baseSelector.$("span[title^=base]").shouldHave(Condition.attribute("title", String.format("base: %s", name)));
        $(".blankslate").shouldNot(Condition.text(blankslateText));
        return this;
    }

    public OpenPullRequestPage setCompare(String name) {
        String blankslateText = $(".blankslate").getText();
        compareSelector.click();
        compareSelector.$("button").shouldHave(Condition.attribute("aria-expanded", "true"));
        compareSelector.$("input").setValue(name);
        compareSelector.$(".navigation-focus").shouldHave(Condition.text(name)).click();
        compareSelector.$("span[title^=compare]").shouldHave(Condition.attribute("title", String.format("compare: %s", name)));
        $(".blankslate").shouldNot(Condition.text(blankslateText));
        return this;
    }

    public OpenPullRequestPage setTitle(String title) {
        $("#pull_request_title").setValue(title);
        return this;
    }

    public PullRequestPage clickOnCreatePullRequest() {
        $("#new_pull_request button[type=submit]").click();
        return page(PullRequestPage.class);
    }
}
