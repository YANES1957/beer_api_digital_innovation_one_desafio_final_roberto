Feature: Gerenciar cervejas

  Scenario: Adicionar uma cerveja
    Given que eu tenho uma cerveja nova
    When eu adicionar a cerveja ao estoque
    Then a cerveja deve ser registrada com sucesso
