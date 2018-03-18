package user_interface.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

public class RepositoryPage extends RepositoryBasePage {

    public RepositoryPage createNewBranch(String branch) {
        $(".branch-select-menu").click();
        $("#context-commitish-filter-field").should(Condition.appear).setValue(branch);
        $(".js-new-item-name").should(Condition.appear).click();
        return this;
    }
}
