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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class Settings {

    static String scenario;

    public static String getPropertyFromFile(String key) {
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

    public static void setUp() {
        closeWebDriver();
        setDriver();
        setTimeout();
    }

    private static void setTimeout() {
        String timeout = getPropertyFromFile("selenide.timeout");
        Configuration.timeout = StringUtils.isNumeric(timeout) ? Integer.parseInt(timeout) * 1000 : 8000;
    }

    private static void setDriver() {
        String url = getPropertyFromFile("selenium.grid");
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
