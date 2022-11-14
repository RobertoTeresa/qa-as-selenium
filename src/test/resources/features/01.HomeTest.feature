@as @portada

Feature: Casos de prueba sobre la página principal de Diario AS

  Background:
    Given El usuario se encuentra en la página de inicio
    And El usuario acepta el pop-up de cookies

  @AUT-1 @publicidad @severity=minor
  Scenario: Carga de contenedores SKY en caso de no tener SKIN en la portada
    When El usuario espera a que cargue la página por completo
    Then El usuario visualiza el contenedor SKIN o en su lugar los SKY
    And En caso de no haber SKIN los SKY tienen las dimensiones indicadas
      | SKY1        | 120x600, 160x600, 421x900, 431x938 |
      | SKY2        | 120x600, 160x600, 421x900, 431x938 |

  @AUT-2 @publicidad @severity=minor
  Scenario Outline: Carga de contenedor de tipo STICKY en la portada
    When El usuario espera a que cargue la página por completo
    And El usuario se desplaza hasta el listado de artículos
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones            |
      | STICKY     | 970x90, 980x90, 728x90 |

  @AUT-3 @publicidad @severity=minor
  Scenario Outline: Carga de contenedores de tipo LDB en la portada
    When El usuario espera a que cargue el elemento "<contenedor>"
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones                              |
      | LDB1       | 728x90, 970x250, 970x90, 980x250, 980x90 |

  @AUT-4 @publicidad @severity=minor
  Scenario Outline: Carga de contenedores de tipo MPU en la portada
    When El usuario espera a que cargue el elemento "<contenedor>"
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones      |
      | MPU1       | 300x250, 300x600 |
      | MPU2       | 300x250, 300x600 |
      | MPU3       | 300x250, 300x600 |