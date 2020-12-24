package pageObjects.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.TestBase;

public class LoginPage extends TestBase {

    private static WebElement element = null;

    public LoginPage() {
    }

    public static WebElement username() {
        element = $(By.xpath("//*[@id='txt-username']"));
        return element;
    }

    public static WebElement password() {
        return $(By.xpath("//*[@id='txt-password']"));
    }

    public static WebElement loginButton() {
        element = $(By.xpath("//*[@id='btn-login']"));
        return element;
    }
}

