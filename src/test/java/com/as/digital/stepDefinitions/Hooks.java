package com.as.digital.stepDefinitions;

import com.as.digital.pages.PagesFactory;
import com.as.digital.utils.Flags;
import com.as.digital.utils.ScreenRecorder;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

public class Hooks {

    /** Variables */
    
    private static WebDriver driver;
    public static final int TIMEOUT = 10;

    /** Delete all cookies at the start of each scenario to avoid shared state between tests */
    @Before
    @SuppressWarnings("deprecation")
    public void setUp(Scenario scenario) {
        String browser = Flags.getInstance().getBrowser();
        if (StringUtils.isBlank(browser)) browser = "chrome";

        switch (browser) {
            case "firefox":
                break;
            case "safari":
                break;
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    @After
    public void evidencias(Scenario scenario) {
        try {
            final byte[] screenByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenByte, "image/png", scenario.getName());
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        driver.close();
    }
}
