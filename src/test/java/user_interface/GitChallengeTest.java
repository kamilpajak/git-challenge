package user_interface;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import user_interface.page.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GitChallengeTest {

    private HomePage homePage;

    @BeforeClass
    public static void setUpClass() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.timeout = 1000 * 8;
        Configuration.collectionsTimeout = 1000 * 8;
    }

    @Before
    public void setUp() {
        closeWebDriver();
        this.homePage = LoginPage.login(
                GitChallengeTest.getProperty("github.login"),
                GitChallengeTest.getProperty("github.password")
        );
    }

    private static String getProperty(String key) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("application.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    @Test
    public void userShouldLoginAndCreateRepository() {
        RepositoryCreationPage repositoryCreationPage = this.homePage.clickOnNewRepositoryButton();
        RepositoryPage repositoryPage = repositoryCreationPage.enterName("lorem-ipsum-dolor")
                .enterDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .addReadme(true)
                .clickOnCreateRepositoryButton();
        assertThat(repositoryPage.getNavigationBar().getRepositoryName(), is("lorem-ipsum-dolor"));
    }

    @Test
    public void userShouldLoginAndPushCommits() {
        RepositoryPage repositoryPage = this.homePage.selectRepository("lorem-ipsum-dolor");
        RepositoryNewFilePage repositoryNewFilePage = repositoryPage.createNewBranch("develop").clickOnCreateNewFile();
        repositoryPage = repositoryNewFilePage.setFilename("Cras et massa.txt").commitNewFile("Cras et massa id metus aliquet rutrum.");
        assertThat(repositoryPage.fileExists("Cras et massa.txt"), is(Boolean.TRUE));
    }

    @Test
    public void userShouldLoginAndDeleteRepository() {
        RepositoryPage repositoryPage = this.homePage.selectRepository("lorem-ipsum-dolor");
        RepositorySettingsPage repositorySettingsPage = repositoryPage.getNavigationBar().clickOnSettings();
        this.homePage = repositorySettingsPage.deleteRepository(GitChallengeTest.getProperty("github.password"));
        assertThat(this.homePage.repositoryExists("lorem-ipsum-dolor"), is(Boolean.FALSE));
    }

}
