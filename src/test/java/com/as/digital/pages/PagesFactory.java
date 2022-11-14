package com.as.digital.pages;

import org.openqa.selenium.*;

public class PagesFactory {

    /** Variables */

    private static PagesFactory pagesFactories;
    private final BasePage basePage;
    private final HomePage homePage;
    private final ArticlePage articlePage;

    /** Constructor */

    private PagesFactory(WebDriver driver) {
        basePage = new BasePage(driver);
        homePage = new HomePage(driver);
        articlePage = new ArticlePage(driver);
    }

    /** Methods */

    public static void start(WebDriver driver) {
        pagesFactories = new PagesFactory(driver);
    }

    public static PagesFactory getInstance() {
        return pagesFactories;
    }

    public BasePage getBasePage() { return basePage; }

    public HomePage getHomePage() { return homePage; }

    public ArticlePage getArticlePage() { return articlePage; }
}