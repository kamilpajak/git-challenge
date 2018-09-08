package step_definitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java8.En;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.validator.routines.UrlValidator;
import user_interface.page.HomePage;
import user_interface.page.LoginPage;
import user_interface.page.repository.*;

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

    private NewRepositoryPage newRepositoryPage;

    private MainPage mainPage;

    private NewFilePage newFilePage;

    private OpenPullRequestPage openPullRequestPage;

    private PullRequestPage pullRequestPage;

    private PullRequestListPage pullRequestListPage;

    private SettingsPage settingsPage;

    public GitChallengeStepDefinitions() {
        Given("^user opens browser with GitHub login page$", () -> {
            setUp();
            loginPage = new LoginPage();
        });
        When("^user fills in Login with data from file$", () -> loginPage.setLogin(getProperty("github.login")));
        And("^user fills in Password with data from file$", () -> loginPage.setPassword(getProperty("github.password")));
        And("^user clicks on Sign In button$", () -> homePage = loginPage.clickOnSignInButton());
        Then("^user lands on home page$", () -> assertThat(url(), is("https://github.com/")));
        When("^user clicks on New Repository button$", () -> newRepositoryPage = homePage.clickOnNewRepositoryButton());
        And("^user fills in Repository name with \"([^\"]*)\"$", (String repositoryName) -> newRepositoryPage.enterName(repositoryName));
        And("^user fills in Description with \"([^\"]*)\"$", (String description) -> newRepositoryPage.enterDescription(description));
        And("^user checks Initialize this repository with README option$", () -> newRepositoryPage.addReadme(true));
        And("^user clicks on Create Repository button$", () -> mainPage = newRepositoryPage.clickOnCreateRepositoryButton());
        Then("^user lands on \"([^\"]*)\" repository page$", (String repositoryName) -> assertThat(mainPage.getNavigationBar().getRepositoryName(), is(repositoryName)));
        When("^user selects repository \"([^\"]*)\"$", (String repositoryName) -> mainPage = homePage.selectRepository(repositoryName));
        And("^user creates a new branch with name \"([^\"]*)\"$", (String branch) -> mainPage.createNewBranch(branch));
        And("^user clicks on Create New File button$", () -> newFilePage = mainPage.clickOnCreateNewFile());
        And("^user fills in Filename with \"([^\"]*)\"$", (String filename) -> newFilePage.setFilename(filename));
        And("^user commits new file with message \"([^\"]*)\"$", (String message) -> mainPage = newFilePage.commitNewFile(message));
        Then("^user sees \"([^\"]*)\" file on a list$", (String filename) -> assertThat(mainPage.fileExists(filename), is(true)));
        And("^user clicks on New Pull Request button$", () -> openPullRequestPage = mainPage.clickOnNewPullRequest());
        And("^user sets Base as \"([^\"]*)\"$", (String branch) -> openPullRequestPage.setBase(branch));
        And("^user sets Compare as \"([^\"]*)\"$", (String branch) -> openPullRequestPage.setCompare(branch));
        And("^user fills in Title with \"([^\"]*)\"$", (String title) -> openPullRequestPage.setTitle(title));
        And("^user clicks on Create Pull Request button$", () -> pullRequestPage = openPullRequestPage.clickOnCreatePullRequest());
        Then("^user lands on \"([^\"]*)\" pull request page$", (String title) -> assertThat(pullRequestPage.getTitle(), is(title)));
        And("^user clicks on Pull Requests button$", () -> pullRequestListPage = mainPage.getNavigationBar().clickOnPullRequests());
        And("^user selects pull request \"([^\"]*)\" from the list$", (String pullRequest) -> pullRequestPage = pullRequestListPage.selectPullRequest(pullRequest));
        And("^user clicks on Merge Pull Request button$", () -> pullRequestPage.clickOnMergePullRequest());
        Then("^user sees that pull request is merged$", () -> assertThat(pullRequestPage.isMerged(), is(true)));
        And("^user clicks on Settings button$", () -> settingsPage = mainPage.getNavigationBar().clickOnSettings());
        And("^user clicks on Delete Repository button$", () -> settingsPage.clickOnDeleteRepository());
        And("^user enters repository name to confirm delete$", () -> homePage = settingsPage.confirmDelete(getProperty("github.password")));
        Then("^user sees that repository \"([^\"]*)\" does not exist$", (String repositoryName) -> assertThat(homePage.repositoryExists(repositoryName), is(false)));
    }

    private static void setUp() {
        closeWebDriver();
        String url = getProperty("selenide.grid");
        if (UrlValidator.getInstance().isValid(url)) {
            Configuration.remote = url;
            Configuration.browser = "chrome";
        } else {
            ChromeDriverManager.getInstance().setup();
            Configuration.browser = WebDriverRunner.CHROME;
        }
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
