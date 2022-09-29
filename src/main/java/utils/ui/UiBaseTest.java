package utils.ui;

import config.BrowserManager;
import lombok.extern.java.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;

@Log
public abstract class UiBaseTest {

    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeMethod
    public void init() {
        WebDriver driver = BrowserManager.doBrowserSetup();
        threadLocalDriver.set(driver);
        BasePage.setDriver(threadLocalDriver.get());
    }

    public static void logStep(String stepDescription) {
        log.info("     STEP: " + stepDescription);
    }

    @AfterMethod
    public void closeSession() {
        threadLocalDriver.get().close();
        threadLocalDriver.remove();
    }
}
