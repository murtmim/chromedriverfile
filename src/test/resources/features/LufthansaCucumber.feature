Feature: Register user account on Lufthansa.com 
	As an user
  I want to create a user account
  So that I get relevant results

@Positive 
Scenario Outline: PositiveScenario 
	Given user is on Lufthansa home page 
	Then select the fields "<countryname>" and "<languagename>"
	And try to register a user account
	And enter the user details
	
	Examples: 
	| countryname| |languagename|
	| India      | |English     |
   