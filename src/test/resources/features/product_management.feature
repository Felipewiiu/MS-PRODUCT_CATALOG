Feature: Product Management

  Scenario: Create a product with negative price
    Given a product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | ELETRONICOS | Sample Product | Sample Description | -10.0 | 10            | 0.0            |
    When the client creates the product
    Then the response status should be 400
    And the error message should be "product cannot have a price lower than zero"

  Scenario: Create a product with negative stock quantity
    Given a product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | ELETRONICOS | Sample Product | Sample Description | 100.0 | -5            | 0.0            |
    When the client creates the product
    Then the response status should be 400
    And the error message should be "Stock quantity must be greater than zero"

  Scenario: Create a product
    Given a product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | ELETRONICOS | Sample Product | Sample Description | 100.0 | 10            | 0.0            |
    When the client creates the product
    Then the response status should be 201
    And the product name should be "Sample Product"

  Scenario: Get a product by ID
    Given a product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | ELETRONICOS | Sample Product | Sample Description | 100.0 | 10            | 0.0            |
    And the product is created
    When the client retrieves the product by ID
    Then the response status should be 200
    And the product name should be "Sample Product"

  Scenario: Update a product
    Given a product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | ELETRONICOS | Sample Product | Sample Description | 100.0 | 10            | 0.0            |
    And the product is created
    When the client updates the existing product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | PAPELARIA   | Updated Product | Updated Description | 150.0 | 20            | 5.0            |
    Then the response status should be 200
    And the product name should be "Updated Product"

  Scenario: Delete a product
    Given a product with the following details
      | category    | name           | description      | price | stockQuantity | discountAmount |
      | ELETRONICOS | Sample Product | Sample Description | 100.0 | 10            | 0.0            |
    And the product is created
    When the client removes the product by ID
    Then the response status should be 204