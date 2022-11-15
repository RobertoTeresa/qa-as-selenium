package com.as.digital.pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public abstract class AbstractPage {

    /** Variables */

    protected Wait<WebDriver> wait;
    private final WebDriver driver;

    /** Constructor */

    AbstractPage(WebDriver driver) {
        this.driver = driver;
        /** wait = new MyFluentWait<>(driver)
                .withTimeout(5, ChronoUnit.SECONDS)
                .pollingEvery(2, ChronoUnit.SECONDS)
                .ignoring(NoSuchElementException.class); */
    }

    /** Methods */

    protected WebDriver getDriver() { return driver; }

    /* ================= */
    /* Wait/Load methods */
    /* ================= */

    protected Wait<WebDriver> getWait() { return wait; }

    protected void setWait(Wait<WebDriver> wait) { this.wait = wait; }

    public void sleepDriver(int seconds) {
        long millis = TimeUnit.SECONDS.toMillis(seconds);
        try { Thread.sleep(millis); } catch (InterruptedException e) { log.info(e.getMessage()); }
    }

    public void waitVisibleByElement(WebElement elem) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(elem));
        } catch (Exception e) { log.info(e.getMessage()); }
        try { Thread.sleep(4000); } catch (InterruptedException e) { log.info(e.getMessage()); }
    }

    public void waitVisibleById(String id) {
        try {
            WebElement elem = getDriver().findElement(By.id(id));
            // new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(elem));
            new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(elem));
        } catch (Exception e) { log.info(e.getMessage()); }
        // try { Thread.sleep(4000); } catch (InterruptedException e) { log.info(e.getMessage()); }
        try { Thread.sleep(10000); } catch (InterruptedException e) { log.info(e.getMessage()); }
    }

    public void waitVisibleByElementWithTime(WebElement elem, int time) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOf(elem));
        } catch (Exception e) { log.info(e.getMessage()); }
    }

    public void waitVisibleByIdWithTime(String id, int time) {
        try {
            WebElement elem = getDriver().findElement(By.id(id));
            new WebDriverWait(getDriver(), Duration.ofSeconds(time)).until(ExpectedConditions.visibilityOf(elem));
        } catch (Exception e) { log.info(e.getMessage()); }
    }

    public boolean isPageLoaded(WebElement elem) {
        boolean isLoaded = false;
        try {
            isLoaded = elem.isDisplayed();
        } catch (NoSuchElementException e) { e.printStackTrace();}
        return isLoaded;
    }

    public boolean isElementPresent(WebElement elem) {
        boolean isPresent;
        try {
            isPresent = elem.isDisplayed();
        } catch (NoSuchElementException e) { isPresent = false; }
        return isPresent;
    }

    /* =================== */
    /* Scroll/Move methods */
    /* =================== */

    public void scrollDown(int pixels){
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0," + pixels + ")");
    }

    public void scrollUp(int pixels){
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,-" + pixels + ")");
    }

    public void scrollToTop(){ ((JavascriptExecutor) driver).executeScript("scroll(document.body.scrollHeight, 0)", ""); }

    public void scrollTo(WebElement elem) {
        if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equals("chrome")) {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(elem).build().perform();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        }
        scrollDown(300);
    }

    public void scrollSearchClick(WebElement elem) {
        try{ elem.click();
        } catch(WebDriverException wde){
            if(wde.getMessage().contains("is not clickable at point")){
                scrollDown(300);
                scrollSearchClick(elem);
            } else{ wde.printStackTrace(); }
        }
    }

    public void moveTo(WebElement elem) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(elem).build().perform();
    }

    public void moveToAndClick(WebElement elem) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(elem).click().build().perform();
    }

    /* =============== */
    /* General methods */
    /* =============== */

    public String getUrl() { return driver.getCurrentUrl(); }

    public void navigateTo(String url) {
        try {
            getDriver().navigate().to(url);
        } catch (java.lang.Exception e) {
            if (e instanceof TimeoutException) {
                log.info("Timeout loading home page");
            } else if (e instanceof ScriptTimeoutException) {
                log.info("Script Timeout loading home page");
            } else {
                log.error(e.getMessage());
            }
        }
    }

    public static void dragAndDropElement(WebDriver driver, By locator, int xOffset, int yOffset){
        WebElement slider = driver.findElement(locator);
        Actions move = new Actions(driver);
        Action action = move.dragAndDropBy(slider, xOffset, yOffset).build();
        action.perform();
    }

    public void switchWindow() {
        for(String winHandle : getDriver().getWindowHandles()){
            getDriver().switchTo().window(winHandle);
        }
    }
}