package user_interface;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import user_interface.page.HomePage;
import user_interface.page.LoginPage;
import user_interface.page.RepositoryCreationPage;
import user_interface.page.RepositoryPage;

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
        RepositoryCreationPage repositoryCreationPage = this.homePage.clickOnNewRepositoryButton();
        RepositoryPage repositoryPage = repositoryCreationPage.enterName("lorem-ipsum-dolor")
                .enterDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .addReadme(true)
                .clickOnCreateRepositoryButton();
    }
}
