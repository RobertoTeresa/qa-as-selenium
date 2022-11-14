# DIARIO AS - SELENIUM (DIGITAL)

[![diario as - selenium](https://github.com/hiberus-prisa-qa-devops/qa-as-selenium/actions/workflows/selenium.yml/badge.svg)](https://github.com/hiberus-prisa-qa-devops/qa-as-selenium/actions/workflows/selenium.yml)
[![Allure](https://img.shields.io/badge/Allure-Ultima%20ejecuci%C3%B3n-blue)](https://hiberus-prisa-qa-devops.github.io/qa-as-selenium/)
[![Confluence](https://img.shields.io/badge/Confleunce-QA%20DevOps-yellow)](https://confluence.t-prisa.com/pages/viewpage.action?pageId=123142852)

## [Resultado de la última ejecución](https://hiberus-prisa-qa-devops.github.io/qa-as-selenium/)

Enlace para acceder a los reportes que ha generado Allure. Por defecto, se visualiza el reporte de la ultima ejecución lanzada. Para poder comprobar las ejecuciones anteriores pulsar sobre cualquier pico del gráfico que aparece en el lado derecho del reporte. 
Configuración: Guardar los últimos 10 reportes.

## Descripción

Este repositorio contiene un proyecto Selenium para automatizar los casos de uso de Diario AS en escritorio. ¿Qué es Selenium? Selenium es una herramienta open-source de control de calidad para automatizar y ejecutar pruebas funcionales y de regresión sobre una aplicación. Para más información consultar la documentación de Confluence.

Los casos han sido solicitados por el equipo de PRISA y se están lanzando sobre la [página de producción](https://as.com/). El plan de pruebas con todos los casos y sus respectivos casos se pueden encontrar documentados en Confluence.
Tambien se pueden consultar los casos dentro de los archivos Cucumber ubicados en la siguiente ruta del proyecto:

```
src/test/resources/features/
```

## Documentación

- [Plan de pruebas de TestLink en Confluence](https://confluence.t-prisa.com/display/QADEVOPS/4.6.3.1.1.+AS)
- [Documentación de Selenium en Confluence](https://confluence.t-prisa.com/pages/viewpage.action?pageId=123149121)
- [Glosario de términos en Confluence](https://confluence.t-prisa.com/pages/viewpage.action?pageId=123149100)

## Workflow

El proyecto corre sobre un sistema operativo `macos-latest` y la batería de pruebas pueden lanzarse tanto en `Google Chrome` como en `Apple Safari`. A su vez, se da la opción de elegir que reportes quieres que se generen después de la ejecución. Las opciones disponibles son:

- Generar y publicar un Allure: Genera un reporte web de alto nivel y trazabilidad, lo almacena en un artefacto y lo despliega en una página de Github. 
- Generar un reporte HTML de ExtentReports: Genera un simple archivo .html y lo almacena en un artefacto. 
- Generar un PDF de ExtentReports: Genera un archivo .pdf y lo almacena en un artefacto. 
- Grabar la ejecución con Monte: Genera un archivo .avi de cada caso y lo almacena en un artefacto.

Para consultar la pipeline con el workflow, consulta el archivo YML ubicado en la siguiente ruta del proyecto:

```
.github/workflows/selenium.yml
```

## Ruta de los artefactos

Para poder comprobar los artefactos generados durante el workflow hay que acceder a la propia ejecución. Para ello podemos seguir los siguientes pasos:

1. Acceder a la pestaña de `Actions`
2. En el menú de la izquierda seleccionar el workflow `diario as - selenium`
3. Seleccionar la ejecución deseada
4. Bajar hasta el fondo de la página al apartado `Artifacts`
5. Pulsar sobre el artefacto deseado 
6. Una vez descargado, descomprimir el archivo .zip

## Tecnologías utilizadas

- Herramienta: Selenium WebDriver
- IDE: IntelIJ
- Compilación: Maven
- Lenguaje: Java
- Framework: JUnit 4
- BDD: Cucumber 7
- Reporte: Allure & ExtentReports

## Requisitos para lanzar en local

En caso de que se quiera probar ejecutar el proyecto en local se necesita cumplir los siguientes requisitos:

- Tener un equipo con al menos 4 GB de RAM
- Disponer del navegador que se vaya a escoger a la hora de lanzar el proyecto
- Conexión decente para que los pasos cuenten con el tiempo necesario
- Java JDK 11 con la ruta home configurada
- Maven con la ruta home configurada
- IntelIj para poder abrir el proyecto y lanzarlo

## Comando para ejecutar Selenium

- Con parametros por defecto (Chrome, sin Docker, sin Proxy)
```sh
mvn clean test
```

- Especificando una etiqueta de cucumber para ejecutar solo ciertos casos (portada, articulo, publicidad, AUT-1)
```sh
mvn clean test -Dcucumber.filter.tags='@<etiqueta>'
```

- Especificando navegador (Chrome, Safari)
```sh
mvn clean test -Dbrowser=<navegador>
```

- Especificando navegador, docker y proxy
```sh
mvn clean test -Dbrowser=<navegador> -Ddocker=<booleano> -Dproxy=<ip>:<puerto>
```
