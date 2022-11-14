package com.as.digital.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseLocators {

    /** Locators */

    @FindBy(id = "didomi-notice-agree-button")
    public WebElement btnAcceptCookies;
}