package user_interface.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryPage extends RepositoryBasePage {

    public RepositoryPage createNewBranch(String branch) {
        $(".branch-select-menu").click();
        $("#context-commitish-filter-field").should(Condition.appear).setValue(branch);
        $(".js-new-item-name").should(Condition.appear).click();
        if (branch.length() > 14) {
            $(".branch-select-menu .js-menu-target").shouldHave(Condition.attribute("title", branch));
        } else {
            $(".branch-select-menu .js-menu-target span").shouldHave(Condition.text(branch));
        }
        return this;
    }

    public RepositoryNewFilePage clickOnCreateNewFile() {
        $(".file-navigation .BtnGroup-form button").click();
        return page(RepositoryNewFilePage.class);
    }

    public RepositoryOpenPullRequestPage clickOnNewPullRequest() {
        $(".new-pull-request-btn").click();
        return page(RepositoryOpenPullRequestPage.class);
    }

    public boolean fileExists(String filename) {
        return $(String.format(".files .js-navigation-item .content a[title=\"%s\"]", filename)).exists();
    }
}
