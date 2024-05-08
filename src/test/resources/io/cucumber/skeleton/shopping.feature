Feature: Shopping

    Scenario Outline: Shopping Hummingbird Printed Sweater
        Given I am on website
        And I am logged user
        When I search item
        And I click in this item
        And I choose size "<selectedSize>"
        And I set quantity "5"
        And I click add to cart button
        And I click checkout button
        And I confirm the address
        And I choose the pick-up method
        And I choose the payment method
        And I click order with an obligation to pay
        And I click take the order
        Then I give the confirmation of new order
        And I take screenshot
        Examples:
    | selectedSize |
    | M            |

    Scenario:  History of order - check the last order
        Given I am on website
        And I am logged user
        And I click user icon
        When I click orders history
        Then I see the last order
