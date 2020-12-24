package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScreenShot extends DriverBase {
    private static final Logger LOGGER = Logger.getLogger(ScreenShot.class);

    public static boolean createFile(File screenshot) {
        boolean fileCreated = false;

        if (screenshot.exists()) {
            fileCreated = true;
        } else {
            File parentDirectory = new File(screenshot.getParent());
            if (parentDirectory.exists() || parentDirectory.mkdirs()) {
                try {
                    fileCreated = screenshot.createNewFile();
                } catch (IOException errorCreatingScreenshot) {
                    LOGGER.error(errorCreatingScreenshot.getMessage(), errorCreatingScreenshot);
                }
            }
        }

        return fileCreated;
    }

    public static void writeScreenshotToFile(WebDriver driver, File screenshot) {
        try {
            FileOutputStream screenshotStream = new FileOutputStream(screenshot);
            screenshotStream.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            screenshotStream.close();
        } catch (IOException unableToWriteScreenshot) {
            LOGGER.error(unableToWriteScreenshot.getMessage(), unableToWriteScreenshot);
        }
    }
}
