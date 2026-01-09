Feature: Filter products by price range
  As a customer,
  I want to filter products by selecting a price range,
  So that I can quickly find items within my budget.

  Scenario: Successfully filter products within a price range
    Given I am on the product listing pager
    When I filter products with minimum price 50 and maximum price 80
    Then I should see only products priced between 50 and 80