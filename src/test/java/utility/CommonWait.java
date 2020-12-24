package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonWait extends TestBase {
    private static final Logger LOGGER = Logger.getLogger(CommonWait.class);
    private static final long TIMEOUT = 30;

    public void sleepOfThread(int milisecond) {
        try {
            Thread.sleep(milisecond);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public WebElement visibilityOfElementToBeClickable(WebElement element) {
        return (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void visibilityOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void invisibilityOf(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void visibilityOfAllElementsOfWait(List<WebElement> listResult) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElements(listResult));
    }

    public void visibilityOfElementLocated(WebElement element) {
        String locator = generateXPATH(element, "");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""+ locator +"")));
    }

    public String generateXPATH(WebElement element, String definedElementString)
    {
        visibilityOf(element);
        String childTag = element.getTagName();
        if (childTag.equals("html"))
        {
            return "/html[1]" + definedElementString;
        }
        WebElement parentElement = element.findElement(By.xpath(".."));
        List<WebElement> childrenElements = parentElement.findElements(By.xpath("*"));
        int count = 0;
        for (int i = 0; i < childrenElements.size(); i++)
        {
            WebElement childrenElement = childrenElements.get(i);
            String childrenElementTag = childrenElement.getTagName();
            if (childTag.equals(childrenElementTag))
            {
                count++;
            }
            if (element.equals(childrenElement))
            {
                return generateXPATH(
                        parentElement,
                        "/" + childTag + "[" + count + "]" + definedElementString);
            }
        }
        return null;
    }
}


