package step_definitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java8.En;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import user_interface.page.HomePage;
import user_interface.page.LoginPage;
import user_interface.page.RepositoryCreationPage;
import user_interface.page.RepositoryPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GitChallengeStepDefinitions implements En {

    private LoginPage loginPage;

    private HomePage homePage;

    private RepositoryCreationPage repositoryCreationPage;

    private RepositoryPage repositoryPage;

    public GitChallengeStepDefinitions() {
        this.Given("^user opens browser with GitHub login page$", () -> {
            setUp();
            this.loginPage = new LoginPage();
        });
        this.When("^user fills in Login with data from file$", () -> {
            this.loginPage.setLogin(getProperty("github.login"));
        });
        this.And("^user fills in Password with data from file$", () -> {
            this.loginPage.setPassword(getProperty("github.password"));
        });
        this.And("^user clicks on Sign In Button$", () -> {
            this.homePage = this.loginPage.clickOnSignInButton();
        });
        this.Then("^user lands on home page$", () -> {
            assertThat(url(), is("https://github.com/"));
        });
        this.When("^user clicks on New Repository Button$", () -> {
            this.repositoryCreationPage = this.homePage.clickOnNewRepositoryButton();
        });
        this.And("^user fills in Repository name with \"([^\"]*)\"$", (String repositoryName) -> {
            this.repositoryCreationPage.enterName(repositoryName);
        });
        this.And("^user fills in Description with \"([^\"]*)\"$", (String description) -> {
            this.repositoryCreationPage.enterDescription(description);
        });
        this.And("^user clicks on Create Repository Button$", () -> {
            this.repositoryPage = this.repositoryCreationPage.clickOnCreateRepositoryButton();
        });
        this.Then("^user lands on \"([^\"]*)\" repository page$", (String repositoryName) -> {
            String fetchedRepositoryName = this.repositoryPage.getNavigationBar().getRepositoryName();
            assertThat(fetchedRepositoryName, is(repositoryName));
        });
    }

    private static void setUp() {
        closeWebDriver();
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = WebDriverRunner.CHROME;
        Configuration.timeout = 1000 * 8;
        Configuration.collectionsTimeout = 1000 * 8;
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
}
