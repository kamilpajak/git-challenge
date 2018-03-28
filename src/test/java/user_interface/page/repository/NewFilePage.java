package user_interface.page.repository;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NewFilePage extends RepositoryBasePage {

    public NewFilePage setFilename(String filename) {
        $("[name=filename]").setValue(filename).pressEnter();
        return this;
    }

    public MainPage commitNewFile() {
        $("#submit-file").click();
        return page(MainPage.class);
    }

    public MainPage commitNewFile(String comment) {
        $("#commit-summary-input").setValue(comment);
        $("#submit-file").click();
        return page(MainPage.class);
    }
}
