Feature: TCS Page Validation

As a user 
I want to check the services sublink values are visible

Scenario: Page Validation three

Given user is on TCS homepage three
Then user click on the services tab control redirect to services page
And user verify the assurance service link is present under services
And user verify the platform solution link is present under services
