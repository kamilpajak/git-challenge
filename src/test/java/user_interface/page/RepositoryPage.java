package user_interface.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryPage extends RepositoryBasePage {

    public RepositoryPage createNewBranch(String branch) {
        $(".branch-select-menu").click();
        $("#context-commitish-filter-field").should(Condition.appear).setValue(branch);
        $(".js-new-item-name").should(Condition.appear).click();
        $(".branch-select-menu .js-menu-target").shouldHave(Condition.attribute("title", branch));
        return this;
    }

    public RepositoryNewFilePage clickOnCreateNewFile() {
        $(".file-navigation .BtnGroup-form button").click();
        return page(RepositoryNewFilePage.class);
    }
}
