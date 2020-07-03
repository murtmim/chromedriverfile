Feature: Search product on Jcpenny.com 
	As an user
  I want to enter some product on search box
  So that I get relevant results

@Positive 
Scenario Outline: StoryID PositiveScenario 
	Given user is on Google home page 
	When enter "<srchString>" text into google search field
	
	Examples: 
	| srchString| 
	| Text1     | 
	| Text2     | 
	 
	
	
	
	
	
     