package user_interface.page.repository;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class PullRequestPage extends RepositoryBasePage {

    public String getTitle() {
        return $(".js-issue-title").getText();
    }

    public PullRequestPage clickOnMergePullRequest() {
        $(".merge-message [type=submit]").click();
        $(".commit-form-actions [type=submit]").click();
        $(".TableObject-item .State").shouldHave(Condition.text("merged"));
        return this;
    }

    public boolean isMerged() {
        return $(".TableObject-item .State").getText().equalsIgnoreCase("merged");
    }
}
