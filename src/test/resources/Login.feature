Feature: Login

  An a user
  I want to sign in
  So I can use the app

  @automated
  Scenario: User can sign in with valid value
    Given user is in Katalon login page2
    When user sets username as "John Doe" and password as "ThisIsNotAPassword"
    And user clicks on login button
    Then user sees appointment page
