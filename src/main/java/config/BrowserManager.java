package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BrowserManager {

    public static WebDriver doBrowserSetup() {
        WebDriver driver;
        //steup chrome browser
        WebDriverManager.chromedriver().setup();

        //Add options for --headed or --headless browser launch
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("-headed");

        //initialize driver for chrome
        driver = new ChromeDriver();

        //maximize window
        driver.manage().window().maximize();

        //add implicit timeout
        driver.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(40));

        return driver;
    }
}
