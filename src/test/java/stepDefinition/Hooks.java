package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import utility.DriverBase;
import utility.GetPropertiesValue;
import utility.ScreenShot;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Hooks extends DriverBase {
    private static final Logger LOGGER = Logger.getLogger(Hooks.class);
    private static WebDriver driver;
    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void BeforeScenario() {
        try {
            instantiateDriverObject();
            driver = DriverBase.getDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    @After
    public void AfterScenario(Scenario scenario) {
        try {
            takeScreenShot(scenario);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            clearCookies();
            closeDriverObjects();
        }
    }

    private static void takeScreenShot(Scenario scenario) {
        driver = getDriver();
        String screenshotDirectory = System.getProperty("screenshotDirectory", "target/screenshots");
        String screenshotAbsolutePath = screenshotDirectory + File.separator + System.currentTimeMillis() + "_" + scenario.getName() + ".png";
        File screenshot = new File(screenshotAbsolutePath);
        if (ScreenShot.createFile(screenshot)) {
            try {
                ScreenShot.writeScreenshotToFile(driver, screenshot);
            } catch (ClassCastException weNeedToAugmentOurDriverObject) {
                LOGGER.error(weNeedToAugmentOurDriverObject.getMessage());
                ScreenShot.writeScreenshotToFile(new Augmenter().augment(driver), screenshot);
            }
        } else {
            System.err.println("Unable to create " + screenshotAbsolutePath);
        }
    }

}
