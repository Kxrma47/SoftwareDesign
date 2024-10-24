# Cinema Employee Management Application

## Overview
The **Cinema Employee Management Application** is a console-based Kotlin application designed to facilitate the management of cinema operations, including ticket purchases, refunds, seat tracking, and editing movie/session information. The application saves data locally in CSV files for persistence and supports single-screen cinema operations.

## Features
1. **Ticket Purchase**  
   - Employees can select a movie, session, and seat to complete a ticket sale.
   - Seat availability is updated after purchase.

2. **Ticket Refund**  
   - Employees can refund tickets before the session starts.
   - The refunded seat becomes available again.

3. **Seat Availability Tracking**  
   - View available and booked seats for any session.
   
4. **Edit Movies and Sessions**  
   - Edit movie titles, directors, and session schedules (password-protected feature).

5. **Data Saving**  
   - Saves current state of movies, sessions, and tickets to CSV files.
   
6. **Exit Application**  
   - Exiting the application prompts for saving any unsaved changes.

## Installation and Setup
1. Clone the repository and ensure Kotlin is installed.
2. Run the application by executing the `Main.kt` file.
3. Ensure the application can access `data/tickets.csv`, `data/movie.csv`, and `data/seans.csv` for proper functionality.

## Usage

1. **Start the Application**  
   - Execute `Main.kt` to start the application.

2. **Main Menu Options**:
   - **1**: Purchase a ticket by selecting a movie, session, and seat.
   - **2**: Refund a ticket by selecting the ticket to be refunded.
   - **3**: View available and booked seats for a session.
   - **4**: Edit movie/session details (requires password).
   - **5**: Save changes made during the session.
   - **6**: Exit the application (with option to save).

## Workflow Example

1. **Buying a Ticket**:
   - Employee selects movie and session.
   - Available seats are shown, and employee picks a seat.
   - Ticket is confirmed, and seat is marked as booked.

2. **Refunding a Ticket**:
   - Employee selects a ticket to be refunded.
   - Ticket is returned, and the seat becomes available.

3. **Editing Movie/Session Details**:
   - Employee enters the correct password.
   - Movie or session details are edited.
   - Changes are saved automatically.

4. **Data Saving and Exiting**:
   - Employees save data to ensure all operations are persistent.
   - Exiting without saving results in reverting to the previous state.

## Data Persistence
- Data is stored in CSV files:
  - `tickets.csv` for ticket sales.
  - `movie.csv` for movie details.
  - `seans.csv` for session schedules.
  
## File Structure
```
- src/
  - app/
    - StartApp.kt (Main app workflow)
    - Main.kt (Entry point)
  - entity/
    - Movie.kt
    - Seans.kt
    - Ticket.kt
  - functional/
    - TicketsService.kt
    - Editing.kt
  - util/
    - Util.kt (File operations)
- data/
  - tickets.csv
  - movie.csv
  - seans.csv
```
