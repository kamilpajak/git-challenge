package user_interface.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RepositoryNewFilePage extends RepositoryBasePage {

    public RepositoryNewFilePage setFilename(String filename) {
        $("[name=filename]").setValue(filename).pressEnter();
        return this;
    }

    public RepositoryPage commitNewFile() {
        $("#submit-file").click();
        return page(RepositoryPage.class);
    }

    public RepositoryPage commitNewFile(String comment) {
        $("#commit-summary-input").setValue(comment);
        $("#submit-file").click();
        return page(RepositoryPage.class);
    }
}
