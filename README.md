# My Personal Project - Money Loaning Tracker 💵

## Description (Proposal)
Ever had difficulty keeping track of how much money you owe your friends or how much they owe you? In my daily life, I frequently find myself borrowing or lending money to/from friends whenever one of us forgets to bring our wallet or doesn't have enough change. We have even tried keeping track of every time one person buys the other a coffee, but this happens quite frequently and is difficult to keep track of. Currently, we are using a generic notes application to keep track of these exchanges, however, this is inefficient and doesn't show when and what the money was for — hence the idea for a **Money Loaning Tracker**. 

This program would ideally be used by individuals or groups of friends/colleagues/acquaintances in similar situations as described above to help keep track of who owes money to whom. The application's core features are listed below under *User Stories*.

## User Stories
As a typical user, I want to be able to:
- add a new contact to whom I owe money or who owes me money, to my list of contacts
- add new transaction(s) to an existing contact, and include details such as the date and amount owed
- edit any transaction by changing the contact, amount owed, or date (option only available on console-based version)
- view a list of the people (my contacts) and their overall owed balance
- view a list of transactions for a selected contact
- when I select the quit option, have the option to save my list of contacts (and overall owed balances) to a file
- optionally load my account data from a file when the program starts

## Instructions for Grader
- Note: When the application is first started, it will prompt the user for their name to use in a welcome message. This is purely optional to make the user feel more welcome.
- You can generate the first required event by clicking the ```new contact``` button. This will prompt you to enter a contact name for this contact (will not allow any duplicates) and will create a new contact, which is automatically added to your contact list. You can view the full contact list by clicking the ```view contact``` button in the main menu.
- You can generate the second required event by clicking the ```add transaction``` button. Given you already have at least one contact, this will create a new transaction by prompting for a contact, amount, and date of transaction, and will adjust the contact's balance accordingly (this change is reflected in the full contact history under ```view contact```) 
- You can locate my visual component by opening the application: the logo is an image at the top, and each of the buttons are individual images. 
- You can trigger my audio component by clicking any of the buttons. The ```load data``` and ```save and quit``` buttons play a different sound then the rest of the buttons.
- You can save the state of my application by clicking the ```save and quit``` button. This will also terminate the application once data is saved. To leave without saving, click the x in the top lefthand corner of the window.
- You can reload the state of my application by clicking the ```load data``` button. Note that if you do this after having creeated contacts/transactions it will completely replace your current activity!
- Note: you can return to the main menu at any time by simply exiting the current window that you are in.

## Phase 4: Task 2
The ```DateChecker``` class within ```model``` is an example of a robust class. Within the class, the method ``checkDate()``` throws an ```InvalidDateException``` or a ```ParseException``` depending on the circumstances under which it is called. These exceptions are both checked exceptions, where InvalidDateException is a new class that I created to represent dates that are out of bounds: i.e. before Jan 1, 1900 or after Dec 31, 2100.

In the test class, I have thus tested for the following scenarios:
- no exceptions are expected, the date is a valid date and the method should return true
- InvalidDateException expected because the date is out of bounds; the method should return false
- ParseException expected because the date looks like it could be a date but does not exist (e.g. february 30th)
- ParseException expected because the date is not formatted correctly 
- ParseException expected because the date is null or is a string that is not a date

This class is also used in other classes to make them robust as well.

## Phase 4: Task 3
Some areas that needed improvement in the code were:
- method-level cohesion can be improved in the method ```createWindow()``` in ```LoanAppGUI```: the logo being added at the top of the window can be extracted to a separate method so that this method is really only taking care of one thing, which is to create the window and each of the buttons within it instead of also having to deal with creating and formatting the logo within it as well
- also within the ```LoanAppGUI``` class, each of the buttons and the logo are manually set to a place (using x and y) in the window, however, this presents high coupling because if the logo or any of the buttons' positions are changed, each of the other buttons' positions would need to be manually changed as well in order to maintain even spacing between the buttons and logo. This would not show up as an error anywhere, but instead would become apparent when viewing the gui and could be a pain to manually change later on down the road.
- the ```LoanApp``` class also demonstrates poor cohesion: it is a very long class that can be broked down into several smaller classes so that each class follows the Single Responsibility Principle 

These were each addressed and resolved in the commit with commit message 'Phase 4: Task 3 Cohesion and Coupling changes'.