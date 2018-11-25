package user_interface;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;

public abstract class Repository extends Base {

    public SettingsPage settings() {
        $(".reponav-item", 6).click();
        return Selenide.page(SettingsPage.class);
    }
}
