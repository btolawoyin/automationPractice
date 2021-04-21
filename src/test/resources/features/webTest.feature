Feature: Automation Practice

  @WebTest
  Scenario: Order T-Shirt
    Given I am on the automation practice website
    When I login to my customer account
    And I order a t-shirt
    Then I see ordered t-shirt in the order history

  @WebTest
  Scenario: Update Personal Information
    Given I am on the automation practice website
    When I login to my customer account
    And I click on My Personal Information page
    And I update my first name
    Then I confirm name change