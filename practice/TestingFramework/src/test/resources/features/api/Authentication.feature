@api
Feature: Authentication Endpoint

  Background:
    Given User is at the base URL

  Scenario Template: Valid Credentials test
    When User enters valid Username "<username>", valid Password "<password>" and RememberMe "<rememberMe>" at endpoint "/authenticate"
    Then User checks if they Receive correct Status Code 200
    Examples:
      |username|password|rememberMe|
      |admin   |admin   |true      |
      |admin   |admin   |false     |
      |user    |user    |true      |
      |user    |user    |false     |

  Scenario Template: Bad Request Credentials
    When User enters incorrect Username "<username>" or incorrect Password "<password>" and RememberMe "<rememberMe>" at endpoint "/authenticate"
    Then User will get Status Code as 400
    Examples:
      |username|password|rememberMe|
      |admin   |        |true      |
      |user    |        |true      |
      |        |admin   |true      |
      |        |user    |true      |