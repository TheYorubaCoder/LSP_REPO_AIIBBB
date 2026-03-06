# Class Design Documentation

---

## Class: `FlightInformation`

**Responsibilities:**
- Holds a flight information object that includes aircraft type and other flight data
- Contains methods to access/set specific flight information

**Collaborators:** —

**Assumptions:** —

---

## Class: `Transponder`

**Responsibilities:**
- Validates that packet contains all flight information needed and is as long as needed
- Broadcasts `FlightPacket` object

**Collaborators:**
- `Receiver`

**Assumptions:** Receives raw byte packet from aircraft hardware containing aircraft type and flight data

---

## Class: `Receiver`

**Responsibilities:**
- Parses raw packet into `FlightInformation` object
- Contains `getFlightInfo` method that allows for `FlightInformation` object to be accessed

**Collaborators:**
- `FlightInformation`
- `Transponder`

**Assumptions:** Receives raw byte packet from `Transponder` containing aircraft type and flight data

---

## Class: `DatabaseGenerator`

**Responsibilities:**
- Handles the logic of how the database of flight information is generated
- Checks if database has been created or not
- Creates database and writes `FlightInformation` object into the database
- Updates the database if it already exists

**Collaborators:**
- `FlightInformation`
- `Receiver`

**Assumptions:** Database is a separate SQL file that is written to and read from a given filepath

---

## Class: `DatabaseReader`

**Responsibilities:**
- Checks if data exists
- Reads database
- Fetches all data in the database and returns it as a list of `FlightInformation` objects

**Collaborators:** —

**Assumptions:** Database is a separate SQL file that is written to and read from a given filepath

---

## Class: `Display`

**Responsibilities:**
- Reads database and displays data every 10 seconds
- Has a `whatIsOnDisplay` method that returns a list of flight info objects / most recent database snapshot from the most recent data read

**Collaborators:**
- `DatabaseReader`

**Assumptions:** Database is a separate SQL file that is written to and read from a given filepath

---

## Class: `DangerDetection`

**Responsibilities:**
- Reads database and evaluates all possible dangers
- Has a `detectDanger` method that fetches and analyzes data in the database
- Begins checking the database as long as data is available

**Collaborators:**
- `DatabaseReader`

**Assumptions:** Self-triggered as soon as at least one line of data is contained in the database

---

## Class: `Controller`

**Responsibilities:**
- Calls `whatIsOnDisplay` and, based on different conditions, performs queries
- Reads database and compares data to individual `FlightInformation` objects obtained from `Receiver`

**Collaborators:**
- `Display`
- `DatabaseGenerator`
- `DatabaseReader`
- `Receiver`

**Assumptions:** Queries arise mainly when there are discrepancies between the `Receiver` and the database generated in `DatabaseGenerator`