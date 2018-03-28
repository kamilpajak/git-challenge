package user_interface.page.repository;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class PullRequestListPage extends RepositoryBasePage {

    public PullRequestPage selectPullRequest(String name) {
        $("#js-issues-search").setValue(name).pressEnter();
        $("[id^=issue_] .js-navigation-open").shouldHave(Condition.text(name)).click();
        return page(PullRequestPage.class);
    }
}
