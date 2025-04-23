@api
Feature: Bank Account Endpoint

  Background:
    Given User is at the Base URL

  Scenario: Valid Get Bank Account Requests
    When I send a GET request to endpoint "/bank-accounts"
    Then User checks if they Receive Status Code 200
    Then the response content type should be "application/json"

  Scenario Template: Valid Bank Accounts Post
    When User enters valid "<name>" and "<balance>" at endpoint "/bank-accounts"
    Then User checks if the Status Code is 201
    Examples:
      | name    | balance |
      | Zhongli | 1000.00 |
      | Albedo  | 2000.00 |
      | Klee    | 3000.00 |
      | Diluc   | 4000.00 |
      | Fischl  | 5000.00 |

  Scenario: Invalid Bank Accounts Post
    When User enters empty fields at endpoint "/bank-accounts"
    Then User checks if the Status Code is 405

  Scenario Template: Valid get a particular bank account
    When I send get request to this particular endpoint "/bank-accounts/<id>"
    Then User checks if the Status Code is 200
    Examples:
      | id   |
      | 1007 |
      | 1002 |
      | 1    |
      | 2    |
      | 3    |

  Scenario Template: Invalid get a particular bank account
    When I send get request to this particular endpoint "/bank-accounts/<id>"
    Then User checks if the Status Code is 404
    Examples:
      | id    |
      | 10000 |
      | 10001 |
      | 0     |

  Scenario Template: Valid patch Bank Account operation
    When I send a Patch request to this endpoint "/bank-accounts/<id>" where name is changed to "<name>" for id "<id>"
    Then User checks if the Status Code is 200
    Examples:
      | id   | name    |
      | 1007 | Klee    |
      | 1002 | zhongli |

  Scenario Template: Invalid patch Bank Account operation
    When I send a Patch request to this endpoint "/bank-accounts/<id>" where name is changed to "<name>" for id "<id>"
    Then User checks if the Status Code is 400
    Examples:
      | id    | name    |
      | 10001 | Klee    |
      | 10002 | zhongli |

  Scenario Template:  Valid put Bank Account operation
    When I send a Put request to this endpoint "/bank-accounts/<id>" where name is changed to "<name>" and balance to "<balance>" for id "<id>"
    Then User checks if the Status Code is 200
    Examples:
      | id   | name    | balance   |
      | 1007 | Klee    | 20000.0   |
      | 1002 | Zhongli | 1000000.0 |
      | 1003 | Albedo  | 1000001.0 |

  Scenario Template:  Invalid put Bank Account operation
    When I send a Put request to this endpoint "/bank-accounts/<id>" where name is changed to "<name>" and balance to "<balance>" for id "<id>"
    Then User checks if the Status Code is 400
    Examples:
      | id    | name    | balance   |
      | 10001 | Klee    | 20000.0   |
      | 10002 | Zhongli | 1000000.0 |
      | 10003 | Albedo  | 1000001.0 |

  Scenario Template: Deletion of bank Account
    When I send a Delete request to this endpoint "/bank-accounts/<id>"
    Then User checks if the Status Code is 204
    Examples:
      | id   |
      | 1042 |
      | 1041 |
      | 1044 |
