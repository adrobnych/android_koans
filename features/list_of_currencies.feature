Feature: List of currencies feature

  Scenario: As a user of new app I can load list of currencies
    When I touch the "SHOW CURRENCIES" text
    Then I wait for the "CurrenciesActivity" screen to appear
	  And I should not see "Afghani"
	  When I touch the "Update" text
	  Then I see "Afghani"

  
