package step_definitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java8.En;
import io.github.bonigarcia.wdm.ChromeDriverManager;
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
        this.And("^user clicks on Sign In button$", () -> {
            this.homePage = this.loginPage.clickOnSignInButton();
        });
        this.Then("^user lands on home page$", () -> {
            assertThat(url(), is("https://github.com/"));
        });
        this.When("^user clicks on New Repository button$", () -> {
            this.newRepositoryPage = this.homePage.clickOnNewRepositoryButton();
        });
        this.And("^user fills in Repository name with \"([^\"]*)\"$", (String repositoryName) -> {
            this.newRepositoryPage.enterName(repositoryName);
        });
        this.And("^user fills in Description with \"([^\"]*)\"$", (String description) -> {
            this.newRepositoryPage.enterDescription(description);
        });
        this.And("^user checks Initialize this repository with README option$", () -> {
            this.newRepositoryPage.addReadme(true);
        });
        this.And("^user clicks on Create Repository button$", () -> {
            this.mainPage = this.newRepositoryPage.clickOnCreateRepositoryButton();
        });
        this.Then("^user lands on \"([^\"]*)\" repository page$", (String repositoryName) -> {
            String fetchedRepositoryName = this.mainPage.getNavigationBar().getRepositoryName();
            assertThat(fetchedRepositoryName, is(repositoryName));
        });
        this.When("^user selects repository \"([^\"]*)\"$", (String repositoryName) -> {
            this.mainPage = this.homePage.selectRepository(repositoryName);
        });
        this.And("^user creates a new branch with name \"([^\"]*)\"$", (String branch) -> {
            this.mainPage.createNewBranch(branch);
        });
        this.And("^user clicks on Create New File button$", () -> {
            this.newFilePage = this.mainPage.clickOnCreateNewFile();
        });
        this.And("^user fills in Filename with \"([^\"]*)\"$", (String filename) -> {
            this.newFilePage.setFilename(filename);
        });
        this.And("^user commits new file with message \"([^\"]*)\"$", (String message) -> {
            this.mainPage = this.newFilePage.commitNewFile(message);
        });
        this.Then("^user sees \"([^\"]*)\" file on a list$", (String filename) -> {
            assertThat(this.mainPage.fileExists(filename), is(Boolean.TRUE));
        });
        this.And("^user clicks on New Pull Request button$", () -> {
            this.openPullRequestPage = this.mainPage.clickOnNewPullRequest();
        });
        this.And("^user sets Base as \"([^\"]*)\"$", (String branch) -> {
            this.openPullRequestPage.setBase(branch);
        });
        this.And("^user sets Compare as \"([^\"]*)\"$", (String branch) -> {
            this.openPullRequestPage.setCompare(branch);
        });
        this.And("^user fills in Title with \"([^\"]*)\"$", (String title) -> {
            this.openPullRequestPage.setTitle(title);
        });
        this.And("^user clicks on Create Pull Request button$", () -> {
            this.pullRequestPage = this.openPullRequestPage.clickOnCreatePullRequest();
        });
        this.Then("^user lands on \"([^\"]*)\" pull request page$", (String title) -> {
            assertThat(this.pullRequestPage.getTitle(), is(title));
        });
        this.And("^user clicks on Pull Requests button$", () -> {
            this.pullRequestListPage = this.mainPage.getNavigationBar().clickOnPullRequests();
        });
        this.And("^user selects pull request \"([^\"]*)\" from the list$", (String pullRequest) -> {
            this.pullRequestPage = this.pullRequestListPage.selectPullRequest(pullRequest);
        });
        this.And("^user clicks on Merge Pull Request button$", () -> {
            this.pullRequestPage.clickOnMergePullRequest();
        });
        this.Then("^user sees that pull request is merged$", () -> {
            assertThat(this.pullRequestPage.isMerged(), is(Boolean.TRUE));
        });
        this.And("^user clicks on Settings button$", () -> {
            this.settingsPage = this.mainPage.getNavigationBar().clickOnSettings();
        });
        this.And("^user clicks on Delete Repository button$", () -> {
            this.settingsPage.clickOnDeleteRepository();
        });
        this.And("^user enters repository name to confirm delete$", () -> {
            this.homePage = this.settingsPage.confirmDelete(getProperty("github.password"));
        });
        this.Then("^user sees that repository \"([^\"]*)\" does not exist$", (String repositoryName) -> {
            assertThat(this.homePage.repositoryExists(repositoryName), is(Boolean.FALSE));
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
