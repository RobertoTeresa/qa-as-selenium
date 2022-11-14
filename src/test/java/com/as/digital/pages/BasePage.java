package com.as.digital.pages;

import com.as.digital.locators.BaseLocators;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class BasePage extends AbstractPage {

    /** Variables */

    BaseLocators baseLoc;

    /** Constructor */

    BasePage(WebDriver driver) {
        super(driver);
        this.baseLoc = new BaseLocators();
        PageFactory.initElements(driver, baseLoc);
    }

    /** Methods */

    public void clickAcceptCookies() {
        try {
            waitVisibleByElementWithTime(baseLoc.btnAcceptCookies,3000);
            sleepDriver(1);
            if (isElementPresent(baseLoc.btnAcceptCookies)) baseLoc.btnAcceptCookies.click();
        } catch (Exception e) { log.info(e.getMessage()); }
    }

    public void waitFullPageLoad() {
        waitVisibleById("gtp_diarioas_19753-SKY1");
    }

    public boolean isAdsElementPresent(String id) {
        boolean exists = true;
        try {
            WebElement element = getDriver().findElement(By.id(id));
            scrollTo(element);
            if (!element.isDisplayed()) exists = false;
        } catch ( Exception e) { exists = false; }
        return exists;
    }

    public boolean isAdsSizeCorrect(String id, String size) {
        boolean exists = false;
        List<String> sizeList = new ArrayList<>(Arrays.asList(size.split(", ")));
        WebElement iframeElem = getDriver().findElement(By.cssSelector("div#" + id + " iframe"));
        String sizeElem = iframeElem.getAttribute("width") + "x" + iframeElem.getAttribute("height");
        for (String correctSize : sizeList) {
            if(correctSize.equals(sizeElem)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public String getAdsDimensions(String id) {
        WebElement iframeElem = getDriver().findElement(By.cssSelector("div#" + id + " iframe"));
        return iframeElem.getAttribute("width") + "x" + iframeElem.getAttribute("height");
    }

    public boolean isSkinElementPresent() {
        boolean exists = true;
        try {
            WebElement elem = getDriver().findElement(By.className("raiSkinDesktop"));
            scrollTo(elem);
            if (!elem.isDisplayed()) exists = false;
        } catch ( Exception e) { exists = false; }
        return exists;
    }
}