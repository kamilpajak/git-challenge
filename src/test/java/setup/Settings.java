package setup;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class Settings {

    public static String getProperty(String key) {
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
        String timeout = getProperty("selenide.timeout");
        Configuration.timeout = StringUtils.isNumeric(timeout) ? Integer.parseInt(timeout) * 1000 : 8000;
    }

    private static void setDriver() {
        String url = getProperty("selenium.grid");
        if (UrlValidator.getInstance().isValid(url)) {
            Configuration.remote = url;
            Configuration.browser = "chrome";
        } else {
            ChromeDriverManager.getInstance().setup();
            Configuration.browser = WebDriverRunner.CHROME;
        }
    }
}
