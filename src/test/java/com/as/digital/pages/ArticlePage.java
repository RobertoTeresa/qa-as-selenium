package com.as.digital.pages;

import com.as.digital.locators.ArticleLocators;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class ArticlePage extends AbstractPage {

    /** Variables */

    public static final String ARTICLE_URL = "https://argentina.as.com/futbol/la-argentina-de-messi-se-rompe-n/";
    ArticleLocators articleLoc;

    /** Constructor */

    ArticlePage(WebDriver driver) {
        super(driver);
        this.articleLoc = new ArticleLocators();
        PageFactory.initElements(driver, articleLoc);
    }

    /** Methods */

    public void navigateToArticle() { navigateTo(ARTICLE_URL); }
}