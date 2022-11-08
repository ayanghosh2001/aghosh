
@tag
Feature: Hotel booking
  I want to use this templates

  
	Background:
	Given Go to website


  Scenario Outline:
  Given assert for first page
    When Hotel booking started <name>
    And I enter <fname> <lname> <email> <phone>

    Examples: 
      | name 				 | fname | lname	  |email										|phone			|
      | Hyderabad		 |  Ayan | Ghosh		|ayanghosh13237@gmail.com	|9475325911	|
      | Kolkata			 |  Sayan| Roy   	  |ayanghosh084@gmail.com		|9733490609	|