package setup;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static cucumber.api.Result.Type.PASSED;

public class Hooks {

    @Before
    public void before(Scenario scenario) {
        Settings.scenario = scenario.getName();
    }

    @After
    public void after(Scenario scenario) {
        Cookie cookie = new Cookie("zaleniumTestPassed", scenario.getStatus().equals(PASSED) ? "true" : "false");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        closeWebDriver();
    }
}
