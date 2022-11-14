package com.as.digital.stepDefinitions;

import com.as.digital.pages.*;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleSteps {

    /** Variables */

    PagesFactory pf = PagesFactory.getInstance();
    ArticlePage articlePage = pf.getArticlePage();

    /** Steps */

    @Given("El usuario se encuentra en el artículo indicado")
    public void elUsuarioSeEncuentraEnElArticuloIndicado() { articlePage.navigateToArticle(); }
}