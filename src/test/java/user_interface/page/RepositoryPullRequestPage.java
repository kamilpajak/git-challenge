package user_interface.page;

import static com.codeborne.selenide.Selenide.$;

public class RepositoryPullRequestPage extends RepositoryBasePage {

    public String getTitle() {
        return $(".js-issue-title").getText();
    }
}
