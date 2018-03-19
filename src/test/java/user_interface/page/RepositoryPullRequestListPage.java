package user_interface.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryPullRequestListPage extends RepositoryBasePage {

    public RepositoryPullRequestPage selectPullRequest(String name) {
        $("#js-issues-search").setValue(name).pressEnter();
        $("[id^=issue_] .js-navigation-open").shouldHave(Condition.text(name)).click();
        return page(RepositoryPullRequestPage.class);
    }
}
