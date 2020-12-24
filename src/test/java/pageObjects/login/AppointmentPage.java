package pageObjects.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.TestBase;

public class AppointmentPage extends TestBase {
    private static WebElement element = null;

    public AppointmentPage() {
    }

    public static WebElement facilityBox() {
        return $(By.xpath("//*[@id='combo_facility']"));
    }
}
