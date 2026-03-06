# Class Design Documentation: ATC System

## CRC Card
**Class:** FlightInformation     
**Responsibilities:**
- Holds a flight information object that includes aircraft type and other flight data 
- Contains methods to access/set specific flight information

**Collaborators (if any):** None

**Assumptions (if any):** This is a data class used to pass flight state between other classes.

---

## CRC Card
**Class:** Receiver
**Responsibilities:**
- Unpacks raw high-density packets and extracts aircraft type and flight data
- Generates FlightInformation object from unpacked packet
- Sends FlightInformation to ATCDatabase for storage

**Collaborators (if any):** FlightInformation, ATCDatabase

**Assumptions (if any):** Receives raw byte packets from the aircraft's broadcast signal.

---

## CRC Card
**Class:** ATCDatabase
**Responsibilities:**
- Creates/Initializes the database file based on whether it currently exists
- Writes received FlightInformation objects into the database
- Updates database records with new FlightInformation as updates arrive
- Checks for data existence 
- Fetches all data in the database and returns it as a list of FlightInformation objects

**Collaborators (if any):** FlightInformation

**Assumptions (if any):** Database is a separate SQL file that is written to and read from a given filepath.

---

## CRC Card
**Class:** Display 
**Responsibilities:**
- Reads ATCDatabase and refreshes the graphical display every 10 seconds
- Has a `whatIsOnDisplay` method that returns the current list of flight info objects or the most recent snapshot

**Collaborators (if any):** ATCDatabase

**Assumptions (if any):** Interacts with the database file to ensure the UI remains current.

---

## CRC Card
**Class:** DangerDetection
**Responsibilities:**
- Reads ATCDatabase to evaluate all active flights for potential dangers
- Analyzes flight data to detect dangerous situations (e.g., collisions or restricted airspace)
- Monitors the database continuously as long as data is available

**Collaborators (if any):** ATCDatabase

**Assumptions (if any):** Is self-triggered as soon as at least one record exists in the database.

---

## CRC Card
**Class:** Controller
**Responsibilities:**
- Calls `whatIsOnDisplay` from the Display class to see the current air traffic snapshot
- Allows selection of a specific flight on the display 
- Fetches and displays detailed flight history from the ATCDatabase upon user request

**Collaborators (if any):** Display, ATCDatabase

**Assumptions (if any):** Queries occur based on user interaction with the graphical display interface.