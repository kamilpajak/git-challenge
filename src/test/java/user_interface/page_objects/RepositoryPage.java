package user_interface.page_objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RepositoryPage extends Base {

    @Override
    public void isDisplayed() {
        assertThat(url(), is(String.format("https://github.com/%s", $(".public").getText())));
    }
}
