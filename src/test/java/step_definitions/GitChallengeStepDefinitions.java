package step_definitions;

import cucumber.api.java8.En;
import user_interface.page.LandingPage;
import user_interface.page.LoginPage;
import user_interface.page.repository.*;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static setup.Settings.getProperty;

public class GitChallengeStepDefinitions implements En {

    private LoginPage loginPage;

    private LandingPage landingPage;

    private NewRepositoryPage newRepositoryPage;

    private MainPage mainPage;

    private NewFilePage newFilePage;

    private OpenPullRequestPage openPullRequestPage;

    private PullRequestPage pullRequestPage;

    private PullRequestListPage pullRequestListPage;

    private SettingsPage settingsPage;

    public GitChallengeStepDefinitions() {
        Given("^user opens browser with GitHub login page$", () -> loginPage = new LoginPage());
        When("^user fills in Login with data from file$", () -> loginPage.setLogin(getProperty("github.login")));
        And("^user fills in Password with data from file$", () -> loginPage.setPassword(getProperty("github.password")));
        And("^user clicks on Sign In button$", () -> {
            loginPage.clickOnSignInButton();
            landingPage = new LandingPage();
        });
        Then("^user lands on landing page$", () -> assertThat(url(), is("https://github.com/")));
        When("^user clicks on New Repository button$", () -> newRepositoryPage = landingPage.clickOnNewRepositoryButton());
        And("^user fills in Repository name with \"([^\"]*)\"$", (String repositoryName) -> newRepositoryPage.enterName(repositoryName));
        And("^user fills in Description with \"([^\"]*)\"$", (String description) -> newRepositoryPage.enterDescription(description));
        And("^user checks Initialize this repository with README option$", () -> newRepositoryPage.addReadme(true));
        And("^user clicks on Create Repository button$", () -> mainPage = newRepositoryPage.clickOnCreateRepositoryButton());
        Then("^user lands on \"([^\"]*)\" repository page$", (String repositoryName) -> assertThat(mainPage.getNavigationBar().getRepositoryName(), is(repositoryName)));
        When("^user selects repository \"([^\"]*)\"$", (String repositoryName) -> mainPage = landingPage.selectRepository(repositoryName));
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
        And("^user enters repository name to confirm delete$", () -> landingPage = settingsPage.confirmDelete(getProperty("github.password")));
        Then("^user sees that repository \"([^\"]*)\" does not exist$", (String repositoryName) -> assertThat(landingPage.repositoryExists(repositoryName), is(false)));
    }
}
