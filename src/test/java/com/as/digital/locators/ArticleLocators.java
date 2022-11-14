package com.as.digital.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticleLocators {

    /** Locators */

    @FindBy(xpath = "//a[@name='Navegar a facebook']")
    public WebElement btnFacebook;
}