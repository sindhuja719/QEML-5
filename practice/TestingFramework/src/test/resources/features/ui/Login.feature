@ui
Feature: Login Action
  Scenario Template: Successful Login with Valid Credentials
    Given User with valid Credentials navigates to Login Page
    When User enters valid Username "<username>", valid Password "<password>" and RememberMe "<rememberMe>"
    Then Page Title After Successfully Login In will be "Welcome, Java Hipster!"
    Examples:
      | username | password | rememberMe |
      | admin    | admin    | true       |
      | user     | user     | true       |
      | admin    | admin    | false      |
      | user     | user     | false      |

  Scenario Template:Restrict Login to Invalid Credentials
    Given User with invalid Credentials navigates to Login Page
    When User enters invalid Username "<username>", invalid Password "<password>" and RememberMe "<rememberMe>"
    Then Page will display message "Failed to sign in!"
    Examples:
      | username | password | rememberMe |
      | admin    | user     | true       |
      | user     | admin    | false      |
      | admi n   | admin    | false      |
      | user     | u ser    | true       |
      | use r    | u ser    | true       |
