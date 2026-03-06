# Class Design Documentation

---

## Class: `FlightInformation`

**Responsibilities:**
- Holds a flight information object that includes aircraft type and other flight data
- Contains methods to access/set specific flight information

**Collaborators:** Not needed

**Assumptions:** This is a data class

---

## Class: `Receiver`

**Responsibilities:**
- Unpacks raw packet and extracts aircraft type and flight data
- Generates `FlightInformation` object from unpacked packet
- Sends `FlightInformation` to `ATCDatabase` for storage

**Collaborators:**
- `ATCDatabase`

**Assumptions:** Receives raw byte packets from the aircraft's broadcast signal

---

## Class: `ATCDatabase`

**Responsibilities:**
- Creates database based on whether the database has been created or not
- Writes received `FlightInformation` object into the database
- Updates database with new `FlightInformation` as more come in
- Checks for data existence
- Fetches all data in the database and returns as a list of `FlightInformation` objects

**Collaborators:**
- `FlightInformation`

**Assumptions:** Database is a separate SQL file that is written to and read from a given filepath

---

## Class: `Display`

**Responsibilities:**
- Reads database and displays data every 10 seconds
- Has a `whatIsOnDisplay` method that returns a list of flight info objects / most recent database snapshot from the most recent data read

**Collaborators:**
- `ATCDatabase`

**Assumptions:** Database is a separate SQL file that is written to and read from a given filepath

---

## Class: `DangerDetection`

**Responsibilities:**
- Reads database and evaluates all possible dangers
- Has a `detectDanger` method that fetches and analyzes data in the database
- Begins checking the database as long as data is available

**Collaborators:**
- `ATCDatabase`

**Assumptions:** Self-triggered as soon as at least one line of data is contained in the database

---

## Class: `Controller`

**Responsibilities:**
- Calls `whatIsOnDisplay` to get a snapshot of all flights
- Allows selection of a flight on the display and fetches detailed flight history from the database

**Collaborators:**
- `Display`
- `ATCDatabase`

**Assumptions:** Queries occur based on user interaction with the display