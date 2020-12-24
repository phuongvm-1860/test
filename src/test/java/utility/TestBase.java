package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stepDefinition.Hooks;

import java.util.List;

public class TestBase {
    public static WebDriver driver;
    public static ScenarioContext scenarioContext;

    public TestBase() {
        driver = Hooks.getDriver();
        scenarioContext = new ScenarioContext();
    }

    public static WebElement $(By locator) {
        try {
            return driver.findElement(locator);
        } catch (Exception e) {
            return null;
        }
    }

    public static List<WebElement> $$(By locator) {
        try {
            return driver.findElements(locator);
        } catch (Exception e) {
            return null;
        }
    }
}
