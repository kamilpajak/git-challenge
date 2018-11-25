package setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public final class Settings {

    static String scenario;

    public static Properties properties = new Properties();

    static {
        String appConfigPath = "application.properties";
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUp() {
        closeWebDriver();
        setDriver();
        setTimeout();
    }

    private static void setTimeout() {
        String timeout = properties.getProperty("selenide.timeout");
        Configuration.timeout = StringUtils.isNumeric(timeout) ? Integer.parseInt(timeout) * 1000 : 8000;
    }

    private static void setDriver() {
        String url = properties.getProperty("selenium.grid");
        UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
        if (urlValidator.isValid(url)) {
            setupRemoteWebDriver(url);
        } else {
            setupLocalWebDriver();
        }
    }

    private static void setupRemoteWebDriver(String url) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setCapability("name", scenario);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(URI.create(url).toURL(), capabilities);
            WebDriverRunner.setWebDriver(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static void setupLocalWebDriver() {
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        Configuration.browser = WebDriverRunner.CHROME;
    }
}
