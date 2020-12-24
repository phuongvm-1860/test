package utility;

import config.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DriverBase {
    private static final Logger LOGGER = Logger.getLogger(DriverBase.class);

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactoryThread;


    protected static void clearCookies() {
        try {
            driverFactoryThread.get().getDriver().manage().deleteAllCookies();
        } catch (Exception ignored) {
            LOGGER.error(ignored.getMessage(), ignored);
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }
    }

    protected static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }

    protected static void instantiateDriverObject() {
        driverFactoryThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverFactory = new DriverFactory();
            webDriverThreadPool.add(driverFactory);
            return driverFactory;
        });
    }

    public static WebDriver getDriver() {
        return driverFactoryThread.get().getDriver();
    }

}
