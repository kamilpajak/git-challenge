package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class Hooks {

    @Before
    public void before(Scenario scenario) {
        Settings.scenario = scenario.getName();
    }

    @After
    public void after() {
        closeWebDriver();
    }
}
