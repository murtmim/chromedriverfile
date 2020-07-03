	
	Feature: TCS Page Validation

@one
	Scenario: PageValidation one

	Given user is on TCS homepage one
	Then user verify the link worldwide present
	And user verify the link careers present
	
	@two
	
	Scenario Outline: PageValidation two
	
	Given user is on TCS homepage one
	And user verify the link investors present
	And user verify the link contactus is redirecting to contactus page 
	And user verify the share link present and enter "<srchString>"

	Examples: 
	| srchString| 
	| Services  |


	
