package user_interface.page_objects;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateNewRepositoryPage extends Base {

    @Override
    public void isDisplayed() {
        assertThat(url(), is("https://github.com/new"));
    }
}
