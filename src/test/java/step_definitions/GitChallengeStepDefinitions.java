package step_definitions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java8.En;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import user_interface.page.*;

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

    private RepositoryNewFilePage repositoryNewFilePage;

    private RepositoryOpenPullRequestPage repositoryOpenPullRequestPage;

    private RepositoryPullRequestPage repositoryPullRequestPage;

    private RepositoryPullRequestListPage repositoryPullRequestListPage;

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
            this.repositoryCreationPage = this.homePage.clickOnNewRepositoryButton();
        });
        this.And("^user fills in Repository name with \"([^\"]*)\"$", (String repositoryName) -> {
            this.repositoryCreationPage.enterName(repositoryName);
        });
        this.And("^user fills in Description with \"([^\"]*)\"$", (String description) -> {
            this.repositoryCreationPage.enterDescription(description);
        });
        this.And("^user checks Initialize this repository with README option$", () -> {
            this.repositoryCreationPage.addReadme(true);
        });
        this.And("^user clicks on Create Repository button$", () -> {
            this.repositoryPage = this.repositoryCreationPage.clickOnCreateRepositoryButton();
        });
        this.Then("^user lands on \"([^\"]*)\" repository page$", (String repositoryName) -> {
            String fetchedRepositoryName = this.repositoryPage.getNavigationBar().getRepositoryName();
            assertThat(fetchedRepositoryName, is(repositoryName));
        });
        this.When("^user selects repository \"([^\"]*)\"$", (String repositoryName) -> {
            this.repositoryPage = this.homePage.selectRepository(repositoryName);
        });
        this.And("^user creates a new branch with name \"([^\"]*)\"$", (String branch) -> {
            this.repositoryPage.createNewBranch(branch);
        });
        this.And("^user clicks on Create New File button$", () -> {
            this.repositoryNewFilePage = this.repositoryPage.clickOnCreateNewFile();
        });
        this.And("^user fills in Filename with \"([^\"]*)\"$", (String filename) -> {
            this.repositoryNewFilePage.setFilename(filename);
        });
        this.And("^user commits new file with message \"([^\"]*)\"$", (String message) -> {
            this.repositoryPage = this.repositoryNewFilePage.commitNewFile(message);
        });
        this.Then("^user sees \"([^\"]*)\" file on a list$", (String filename) -> {
            assertThat(this.repositoryPage.fileExists(filename), is(Boolean.TRUE));
        });
        this.And("^user clicks on New Pull Request button$", () -> {
            this.repositoryOpenPullRequestPage = this.repositoryPage.clickOnNewPullRequest();
        });
        this.And("^user sets Base as \"([^\"]*)\"$", (String branch) -> {
            this.repositoryOpenPullRequestPage.setBase(branch);
        });
        this.And("^user sets Compare as \"([^\"]*)\"$", (String branch) -> {
            this.repositoryOpenPullRequestPage.setCompare(branch);
        });
        this.And("^user fills in Title with \"([^\"]*)\"$", (String title) -> {
            this.repositoryOpenPullRequestPage.setTitle(title);
        });
        this.And("^user clicks on Create Pull Request button$", () -> {
            this.repositoryPullRequestPage = this.repositoryOpenPullRequestPage.clickOnCreatePullRequest();
        });
        this.Then("^user lands on \"([^\"]*)\" pull request page$", (String title) -> {
            assertThat(this.repositoryPullRequestPage.getTitle(), is(title));
        });
        this.And("^user clicks on Pull Requests button$", () -> {
            this.repositoryPullRequestListPage = this.repositoryPage.getNavigationBar().clickOnPullRequests();
        });
        this.And("^user selects pull request \"([^\"]*)\" from the list$", (String pullRequest) -> {
            this.repositoryPullRequestPage = this.repositoryPullRequestListPage.selectPullRequest(pullRequest);
        });
        this.And("^user clicks on Merge Pull Request button$", () -> {
            this.repositoryPullRequestPage.clickOnMergePullRequest();
        });
        this.Then("^user sees that pull request is merged$", () -> {
            assertThat(this.repositoryPullRequestPage.isMerged(), is(Boolean.TRUE));
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
