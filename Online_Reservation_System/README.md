# Online Reservation System

A GUI-based **Online Reservation System** developed in Java as part of the **Oasis Infobyte Java Development Internship – Task 1**.

The application allows users to securely log in, search for trains, book train tickets, generate a unique PNR number, view reservation details, and cancel existing reservations using the PNR number.

## 📌 Project Objective

The objective of this project is to develop a desktop-based train reservation system with a graphical user interface and database integration.

The system provides functionality for:

* User authentication
* Train search
* Train ticket booking
* Automatic PNR generation
* Reservation data storage
* Reservation cancellation
* Input validation
* MySQL database integration

## ✨ Features

### 🔐 Login

* Username and password authentication
* Database-based credential verification
* Validation for empty username and password
* Invalid credentials are rejected
* Login success opens the main menu

### 🎫 Train Reservation

Users can enter:

* Passenger name
* Train number
* Train name
* Class type
* Journey date
* Source station
* Destination station

The train name is automatically retrieved from the database after entering a valid train number.

### 🧾 PNR Generation

After successful booking, the system automatically generates a unique **10-digit PNR number**.

The PNR is stored as the primary identifier for the reservation.

### ✅ Booking Confirmation

After a successful reservation, the application displays:

* PNR
* Passenger name
* Train name
* Train number
* Class type
* Journey date
* Source station
* Destination station

### ❌ Reservation Cancellation

Users can:

1. Enter their PNR number
2. Fetch the corresponding reservation
3. View the complete booking details
4. Confirm cancellation
5. Remove the reservation from the database

A confirmation dialog is displayed before cancellation.

### 🛡️ Input Validation

The application validates:

* Required fields
* Passenger name
* Numeric train number
* Journey date format
* Past journey dates
* Source station
* Destination station
* Same source and destination stations

## 🛠️ Technologies Used

* **Java**
* **Java Swing** – Graphical User Interface
* **Hibernate ORM** – Database persistence
* **JPA Annotations**
* **MySQL** – Database
* **Maven/IDE-managed dependencies** – depending on the project setup

## 🏗️ Architecture

The project follows a layered structure:

```text
com.oibsip.reservation
│
├── dao
│   ├── ReservationDAO
│   ├── TrainDAO
│   └── UserDAO
│
├── entity
│   ├── Reservation
│   ├── Train
│   └── User
│
├── ui
│   ├── LoginFrame
│   ├── MainMenuFrame
│   ├── ReservationFrame
│   ├── CancellationFrame
│   └── UITheme
│
└── util
    ├── HibernateUtil
    ├── PnrGenerator
    └── ValidationUtil
```

## 🗄️ Database

The project uses a MySQL database named:

```text
online_reservation
```

The application uses the following main tables:

```text
users
trains
reservations
```

### Users

Stores login credentials.

### Trains

Stores train numbers and train names.

### Reservations

Stores booking information including:

* PNR
* Passenger name
* Train number
* Class type
* Journey date
* Source
* Destination

## 🚀 How to Run

### 1. Install Requirements

Make sure the following are installed:

* Java JDK
* MySQL Server
* MySQL Workbench or another MySQL client
* An IDE such as Eclipse or IntelliJ IDEA

### 2. Create the Database

Create the database in MySQL:

```sql
CREATE DATABASE online_reservation;
```

### 3. Configure Database Connection

Open:

```text
hibernate.cfg.xml
```

Update the MySQL connection settings according to your local MySQL configuration.

Do not commit real database passwords or sensitive credentials to GitHub.

### 4. Prepare Database Tables

Create/populate the required tables:

```text
users
trains
reservations
```

Add at least one user and some train records for testing.

### 5. Run the Application

Run the application's main class from your IDE.

The application opens with the login screen.

## 🔄 Application Flow

```text
Login
  ↓
Main Menu
  ↓
Book Train Ticket
  ↓
Search Train
  ↓
Enter Passenger & Journey Details
  ↓
Validate Input
  ↓
Generate PNR
  ↓
Save Reservation
  ↓
Booking Confirmation
```

Cancellation flow:

```text
Main Menu
  ↓
Cancel Reservation
  ↓
Enter PNR
  ↓
Fetch Booking
  ↓
Display Details
  ↓
Confirm Cancellation
  ↓
Delete Reservation
```

## 📸 Screenshots

Screenshots demonstrating the application's functionality are included in this project repository.

## 🎥 Demo Video

A screen-recorded walkthrough demonstrating the working application is available as part of the internship submission materials.

## 🎓 Internship

**Organization:** Oasis Infobyte
**Internship:** Java Development
**Task:** Task 1 – Online Reservation System

## 👨‍💻 Author

**Yash Ramesh Thubokar**

B.E. Computer Science Engineering

---

⭐ Developed as part of the Oasis Infobyte Java Development Internship.
