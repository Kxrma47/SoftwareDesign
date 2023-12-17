README
======

Cinema Employee Management Application (Interface Russian only)
---------------------------------------------------------------

Cinema Employee Console Application - User Guide

Introduction
-----------
This guide provides instructions on how to use the Cinema Employee Console Application developed in Kotlin. The application is designed to manage cinema operations, including ticket sales, returns, seat tracking, and editing movie and session information.

Getting Started
---------------
To start the application, run the Main.kt file. This will launch the console interface of the application.

Features and Usage
------------------
 1. Buying a Ticket
- Input: Choose the option to buy a ticket. You will be prompted to select a movie and a seat.
- Output: Confirmation of the ticket purchase along with the movie name, session time, and seat number.

 2. Returning a Ticket
- Input: Select the option to return a ticket. You will need to choose the ticket you want to return from the list of sold tickets.
- Output: Confirmation of the ticket return and the seat will be marked as available.

 3. Showing Available and Sold Seats
- Input: Choose to display seats for a specific session. You will need to select the session for which you want to see the seat status.
- Output: A list of seats showing which are available and which are sold.

 4. Editing Movies and Sessions
- Input: Select the editing option. You will be prompted to enter a password. After successful authentication, you can choose to edit movie details or session times.
- Output: Confirmation of the changes made to the movie or session details.

 5. Saving Data
- Input: After making changes or transactions, select the option to save data.
- Output: Confirmation that the current state of data has been saved to the files.

 6. Exiting the Application
- Input: Choose the exit option. The application will ask for confirmation before exiting.
- Output: The application will automatically save the current state and then close.

Example Workflow
-----------------
1. Start the Application: Run Main.kt.
2. Buy a Ticket:
   - Select the option to buy a ticket.
   - Choose a movie and a seat.
   - Receive confirmation of the ticket purchase.
3. Return a Ticket (if needed):
   - Select the return ticket option.
   - Choose the ticket to return.
   - Receive confirmation of the return.
4. Edit Movie/Session Details (if needed):
   - Select the editing option.
   - Enter the password.
   - Make the desired changes.
5. Save Changes:
   - Select the save data option to ensure all changes are stored.
6. Exit the Application:
   - Choose the exit option and confirm to close the application.

Notes
-----
- Data Storage: The application stores data in CSV files. Ensure these files are in the correct location as specified in the code.
- Password for Editing: The default password for editing is set in the code. Ensure you know the password to access editing features.

Troubleshooting
---------------
- Incorrect Inputs: If you enter an incorrect input, the application will prompt you to re-enter the correct information.
- Data Not Saving: Ensure you select the save data option before exiting to retain all changes.

---------------------------------------------------------

This guide should help you navigate and use the Cinema Employee Console Application effectively. For any additional help or queries, refer to the application's source code or contact the development team.

Support:
--------
For any queries or support, please contact mhaque@edu.hse.ru

License:
--------
HSE RU Standard Lincense

Contributors:
-------------
Manager: Alexey Danilov TG: @adan1lov
Haque Munshi Mahidul(Senior Developer)
