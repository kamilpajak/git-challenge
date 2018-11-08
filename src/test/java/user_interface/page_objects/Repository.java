package user_interface.page_objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public abstract class Repository extends Base {

    public SettingsPage settings() {
        $(".reponav-item", 6).click();
        return page(SettingsPage.class);
    }
}
