Feature: Search product on Jcpenny.com 
	As an user
  I want to enter some product on search box
  So that I get relevant results 
	
@Login
Scenario Outline: StoryID LoginScenario 
	Given user is on JCPenny home page 
	When user clicks the My account option 
	And user login with the valid "<userName>" and "<Password>" 
	Then page should shows the user "<Message>" 
	
	Examples: 
		|userName|Password|Message|
		|Parm-3  |Parm-4  |Parm-5 |