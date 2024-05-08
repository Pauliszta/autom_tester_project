Feature: Address

#  Scenario: Navigate to user’s addresses
#    Given I am on website
#    And I am logged user
#    When I type addresses icon
#    Then I am on page with all addresses


  Scenario Outline: Add new address - correct date
    Given I am on website
    And I am logged user
    And I type addresses icon
    And I click to button create new address
    And I am on page with new addresses form
    When I type "<alias>" as alias and "<address>" as address and "<city>" as city and "<postalCode>" as postalCode and "<phone>" as phone
    Then <expectedResult> and "<alias>" as alias and "<address>" as address and "<city>" as city and "<postalCode>" as postalCode and "<phone>" as phone
    Examples:
      | alias | address     | city     | postalCode | phone       | expectedResult        |
      | dom   | Daleka 21   | Poznań   | 60-000     | 733-333-333 | I added new addresses |
      | biuro | Zachodnia 5 | Warszawa | 22-00      | 633-33-52   | I added new addresses |


  Scenario:  Delete the address
    Given I am on website
    And I am logged user
    When I type addresses icon
    Then I am on page with all addresses
    When I click delete button
    Then I deleted the last address