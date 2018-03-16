package user_interface;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GitChallengeTest {

    private HomePage homePage;

    @BeforeClass
    public static void setUpClass() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = WebDriverRunner.CHROME;
    }

    @Before
    public void setUp() {
        this.homePage = LoginPage.login("", "");
    }

    @Test
    public void userShouldLoginAndCreateRepository() {
        NewRepositoryPage newRepositoryPage = this.homePage.clickOnNewRepositoryButton();
        newRepositoryPage.enterName("my-test-repository");
    }
}
