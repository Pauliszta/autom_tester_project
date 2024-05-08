Feature: Login

  Scenario Outline: Login in
    Given I am on website
    When I type "<login>" as login and "<password>" as password
    Then <expectedResult>
    Examples:
    | login            | password | expectedResult |
    | a.nowak@mail.com | test123  | I am logged in |
    | a.nowak@mail.com | abc123   | I am not logged in |
    | b.nowak@mail.com | test123  | I am not logged in |
    | b.nowak@mail.com | abc123   | I am not logged in |


#    Scenario:  Create user - correct data
#      Given I am open browser
#      And I am on website
#      When I type data in the form
#      And I click save button
#      Then New account is created
#
#    Scenario:  Create user - using existed user’s e-mail
#      Given I am open browser
#      And I am on website
#      When I type data in the form
#      And I type e-mail of existed user
#      And I click save button
#      Then New account is not created


