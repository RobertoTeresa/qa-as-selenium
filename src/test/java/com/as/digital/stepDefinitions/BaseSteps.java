package com.as.digital.stepDefinitions;

import com.as.digital.pages.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
public class BaseSteps {

    /** Variables */

    PagesFactory pf = PagesFactory.getInstance();
    BasePage basePage = pf.getBasePage();
    boolean skinExists = false;

    /** Steps */

    @And("El usuario acepta el pop-up de cookies")
    public void elUsuarioAceptaElPopUpDeCookies() {
        basePage.clickAcceptCookies();
    }

    @When("El usuario espera a que cargue la página por completo")
    public void elUsuarioESperaAQueCargueLaPaginaPorCompleto() {
        basePage.waitFullPageLoad();
    }

    @Then("El usuario visualiza el contenedor SKIN o en su lugar los SKY")
    public void elUsuarioVisualizaElContenedorSkinOEnSuLugarLosSky() {
        skinExists = basePage.isSkinElementPresent();
        if (!skinExists){
            boolean exists = basePage.isAdsElementPresent("gtp_diarioas_19753-SKY1");
            assertTrue("No se ha encontrado el contenedor SKY1", exists);
            exists = basePage.isAdsElementPresent("gtp_diarioas_19753-SKY2");
            assertTrue("No se ha encontrado el contenedor SKY2", exists);
        }
    }

    @And("En caso de no haber SKIN los SKY tienen las dimensiones indicadas")
    public void enCasoDeNoHaberSkinLosSkyTienenLasDimensionesIndicadas(DataTable table) {
        if (!skinExists) {
            List<List<String>> tableList = table.asLists(String.class);
            for (List<String> elem : tableList) {
                boolean exists = basePage.isAdsSizeCorrect("gtp_diarioas_19753-" + elem.get(0), elem.get(1));
                String elementSize = basePage.getAdsDimensions("gtp_diarioas_19753-" + elem.get(0));
                String errorMessage = "Las dimensiones de " + elem.get(0) + " son " + elementSize + " y no cumple las indicadas: " + elem.get(1);
                assertTrue(errorMessage, exists);
            }
       }
    }

    @And("El usuario se desplaza hasta el listado de artículos")
    public void elUsuarioSeDesplazaHastaElListadoDeArticulos() {
        basePage.scrollDown(3500);
        basePage.sleepDriver(3);
    }

    @When("El usuario espera a que cargue el elemento {string}")
    public void elUsuarioEsperaAQueCargueElElemento(String name) {
        String id = "gtp_diarioas_19753-" + name;
        basePage.waitVisibleById(id);
    }

    @And("El usuario visualiza el elemento publicitario {string}")
    public void elUsuarioVisualizaElElementoPubliciatario(String name) {
        String id = "gtp_diarioas_19753-" + name;
        boolean exists = basePage.isAdsElementPresent(id);
        assertTrue("No se ha encontrado el contenedor " + name, exists);
    }

    @And("El {string} tiene una de las dimensiones indicadas {string}")
    public void elElementoPublicitarioTieneUnaDeLasDimensionesIndicadas(String name, String size) {
        String id = "gtp_diarioas_19753-" + name;
        boolean exists = basePage.isAdsSizeCorrect(id, size);
        String mensajeError = "Las dimensiones de " + name + " son " + basePage.getAdsDimensions(id) + " y no cumple las indicadas: " + size;
        assertTrue(mensajeError, exists);
    }
}