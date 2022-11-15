package com.as.digital.stepDefinitions;

import com.as.digital.pages.*;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomeSteps {

    /** Variables */

    PagesFactory pf = PagesFactory.getInstance();
    HomePage homePage = pf.getHomePage();

    /** Steps */

    @Given("El usuario se encuentra en la página de inicio")
    public void elUsuarioSeEncuentraEnLaPaginaDeInicio() {
        homePage.navigateToHome();
    }
}