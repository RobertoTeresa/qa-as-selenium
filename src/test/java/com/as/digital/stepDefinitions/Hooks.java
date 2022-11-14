package com.as.digital.stepDefinitions;

import com.as.digital.pages.PagesFactory;
import com.as.digital.utils.Flags;
import com.as.digital.utils.ScreenRecorder;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.*;
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
    public void setUp(Scenario scenario) throws Exception {
        ScreenRecorder.startRecord(scenario.getName());

        String browser = Flags.getInstance().getBrowser();
        if (StringUtils.isBlank(browser)) browser = "chrome";
        boolean isProxy = true;
        String proxyString = Flags.getInstance().getProxy();
        if (StringUtils.isBlank(proxyString)) isProxy = false;
        boolean isDocker = Flags.getInstance().isDocker();

        switch (browser) {
            case "firefox":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                if (isProxy) { optionsFirefox.addArguments("--proxy-server=" + proxyString); }
                if (isDocker) {
                    driver = WebDriverManager.firefoxdriver().capabilities(optionsFirefox).browserInDocker().create();
                } else {
                    driver = WebDriverManager.firefoxdriver().capabilities(optionsFirefox).create();
                }
                break;
            case "safari":
                Proxy proxy = new Proxy();
                proxy.setHttpProxy(proxyString);
                SafariOptions optionsSafari = new SafariOptions();
                if (isProxy) { optionsSafari.setProxy(proxy); }
                if (isDocker) {
                    driver = WebDriverManager.safaridriver().capabilities(optionsSafari).browserInDocker().create();
                } else {
                    driver = WebDriverManager.safaridriver().capabilities(optionsSafari).create();
                }
                break;
            default:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                ChromeOptions optionsChrome = new ChromeOptions();
                if (isProxy) { optionsChrome.addArguments("--proxy-server=" + proxyString); }
                optionsChrome.addArguments("--no-sandbox");
                optionsChrome.addArguments("--disable-gpu");
                optionsChrome.addArguments("--disable-dev-shm-usage");
                optionsChrome.addArguments("--disable-site-isolation-trials");
                if (isDocker) {
                    driver = WebDriverManager.chromedriver().capabilities(optionsChrome).browserInDocker().create();
                } else {
                    driver = WebDriverManager.chromedriver().capabilities(optionsChrome).create();
                }
        }

        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        PagesFactory.start(driver);
    }

    @After
    public void evidencias(Scenario scenario) throws Exception {
        try {
            final byte[] screenByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenByte, "image/png", scenario.getName());
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
        driver.quit();
        ScreenRecorder.stopRecord();
    }
}
