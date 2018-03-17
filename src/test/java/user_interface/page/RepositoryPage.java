package user_interface.page;

import static com.codeborne.selenide.Selenide.$;

public class RepositoryPage {

    public String getRepositoryName() {
        return $(".public strong[itemprop=name]").getText();
    }
}
