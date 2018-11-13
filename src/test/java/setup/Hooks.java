package setup;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class Hooks {

    @Before
    public void before(Scenario scenario) {
        Settings.scenario = scenario.getName();
    }
}
