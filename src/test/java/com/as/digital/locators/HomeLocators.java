package com.as.digital.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeLocators {

    /** Locators */

    @FindBy(css = "article h2.s__tl a")
    public WebElement titleFirstNews;
}