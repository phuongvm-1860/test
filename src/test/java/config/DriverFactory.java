package config;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import static config.DriverType.*;

public class DriverFactory {
    private static final Logger LOGGER = Logger.getLogger(DriverFactory.class);

    private WebDriver driver;
    private DriverType selectedDriverType;

    public DriverFactory() {
        DriverType driverType = CHROME;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            LOGGER.error(ignored.getMessage(), ignored);
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            LOGGER.error(ignored.getMessage(), ignored);
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public WebDriver getDriver() {
        if (null == driver) {
            instantiateWebDriver(selectedDriverType);
        }
        return driver;
    }

    public void quitDriver() {
        driver.quit();
    }

    private void instantiateWebDriver(DriverType driverType) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        driver = driverType.getWebDriverObject(desiredCapabilities);
    }
}
