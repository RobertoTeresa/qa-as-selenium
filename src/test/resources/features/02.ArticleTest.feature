@as @articulo

Feature: Casos de prueba sobre un artículo de Diario AS

  Background:
    Given El usuario se encuentra en el artículo indicado
    And El usuario acepta el pop-up de cookies

  @AUT-5 @publicidad @severity=minor
  Scenario: Carga de contenedores SKY en caso de no tener SKIN en el artículo
    When El usuario espera a que cargue la página por completo
    Then El usuario visualiza el contenedor SKIN o en su lugar los SKY
    And En caso de no haber SKIN los SKY tienen las dimensiones indicadas
      | SKY1        | 120x600, 160x600, 421x900, 431x938 |
      | SKY2        | 120x600, 160x600, 421x900, 431x938 |

  @AUT-6 @publicidad @severity=minor
  Scenario Outline: Carga de contenedor de tipo STICKY en el artículo
    When El usuario espera a que cargue la página por completo
    And El usuario se desplaza hasta el listado de artículos
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones            |
      | STICKY     | 970x90, 980x90, 728x90 |

  @AUT-7 @publicidad @severity=minor
  Scenario Outline: Carga de contenedores de tipo LDB en el artículo
    When El usuario espera a que cargue el elemento "<contenedor>"
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones                              |
      | LDB1       | 728x90, 970x250, 970x90, 980x250, 980x90 |

  @AUT-8 @publicidad @severity=minor
  Scenario Outline: Carga de contenedores de tipo MPU en el artículo
    When El usuario espera a que cargue el elemento "<contenedor>"
    And El usuario se desplaza hasta el listado de artículos
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones      |
      | MPU1       | 300x250, 300x600 |
      | MPU2       | 300x250, 300x600 |
      | MPU3       | 300x250, 300x600 |

  @AUT-9 @publicidad @severity=minor
  Scenario Outline: Carga de contenedor de tipo BTN en el artículo
    When El usuario espera a que cargue el elemento "<contenedor>"
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones      |
      | BTN1       | 300x100          |

  @AUT-10 @publicidad @severity=minor
  Scenario Outline: Carga de contenedor de tipo INTEXT en el artículo
    When El usuario espera a que cargue el elemento "<contenedor>"
    Then El usuario visualiza el elemento publicitario "<contenedor>"
    And El "<contenedor>" tiene una de las dimensiones indicadas "<dimensiones>"
    Examples:
      | contenedor | dimensiones      |
      | INTEXT     | 1x1              |