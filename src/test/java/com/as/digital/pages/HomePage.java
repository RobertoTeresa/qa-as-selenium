package com.as.digital.pages;

import com.as.digital.locators.HomeLocators;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class HomePage extends AbstractPage {

    /** Variables */

    public static final String PAGE_URL = "https://as.com/";
    HomeLocators homeLoc;

    /** Constructor */

    HomePage(WebDriver driver) {
        super(driver);
        this.homeLoc = new HomeLocators();
        PageFactory.initElements(driver, homeLoc);
    }

    /** Methods */

    public void navigateToHome() { navigateTo(PAGE_URL); }
}