Stage 1 - Configuration
Ask for host name and port number.
Stage 2 - Intro / Welcome
Shows the welcome message for the application along with a logo.
Stage 3 - Confirm Flight Booking
Confirms the user wants to book a flight.
Stage 4 - Flight Selection**
Offers the user a drop down menu to select which airline they would like to fly on. After selecting an airline, displays a 
description of that airline.
Stage 5 - Confirm Flight Selection**
Prompts the user to confirm that they want to book a flight with the selected airline. If they do not wish to, they can 
exit or return to the flight selection screen (Stage 4).
Stage 6 - Passenger Information**
The user must enter their first name, last name, and age. You need to validate that the names do not contain special 
characters (aside from dashes) and ages are numbers.
Stage 7 - Passenger Information Confirmation
Ask the user to confirm that their information is correct. Otherwise, return to Stage 6.
Stage 8 - Final Confirmation Screen.
Display the current passenger list for the flight in addition to the user’s boarding pass. Offer the user the option 
to refresh the passenger list.
NOTE: Stages listed above that include a ** should have the following functionality added:
Pressing “\” will launch a new frame that displays airline, the current passengers booked for that airline, and the
flight capacity (Pressing ESC will close this frame).


Server/Client	
You will need to implement a server/client.

Threads	
You will need to have each query request made on its own thread, as well as displaying all GUI 
actions on the Event Dispatch Thread

File I/O	
You will need to perform a series of File I/O Operations to query the current passenger count, passenger list, 
and flights available.

GUI	
You will need to display your progress in a Graphical User Interface.


################################
Project Description
Three airlines exist at Purdue University’s brand new Airport, and they are Delta Airlines, Southwest Airlines, 
and Alaska Airlines. Each airline provides different amenities on board, and it's your job to show your clients 
the differences in each as they go through the process of booking a flight. Once this process is complete, 
it’s up to you to show your passengers other people’s limited information, and show them how full the plane is. 
Once the plane is full, the choice should be removed from the JOptionPane for future users
################################
Page 1:
This is a team project, to be completed with your partner. 
In this project, you will be working with a partner to develop a Graphical User Interface (GUI).
Page 4:
Additional materials for questions relating to Complex GUIs and labeling JFrames are also available in the Resources section.
The server can handle multiple clients simultaneously. It will track and record ticket sales by writing to reservations.txt. 
A sample reservations file can be found in the Starter Code folder
Page 6:
Type Hierarchy
In the Starter Code folder there is an image that documents how all of the classes are supposed to interact.
