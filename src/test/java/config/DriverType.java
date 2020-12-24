package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;

public enum DriverType implements DriverSetup {
    CHROME {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.chromedriver().setup();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", System.getProperty("user.dir")  + File.separator + "src\\test\\resources\\csvFile");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            options.setHeadless(HEADLESS);
            options.addArguments("--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");

            return new ChromeDriver(options);
        }
    },

    FIREFOX {
        public WebDriver getWebDriverObject(DesiredCapabilities capabilities) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(HEADLESS);
            return new FirefoxDriver(options);
        }
    };

    public final static boolean HEADLESS = Boolean.getBoolean("headless");

}
