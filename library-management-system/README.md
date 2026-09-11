# 📚 Library Management System

A modern **Library Management System** built using **Java Spring Boot, Thymeleaf, HTML, CSS, and JavaScript**.

The application provides separate functionality for **library users** and **administrators**, allowing users to browse and manage their library activity while giving administrators complete control over books, users, issued books, fines, reservations, and support queries.

This project was developed as part of my **OASIS Infobyte project work** and is maintained inside the `Thubokar/OBISIP` repository.

---

## ✨ Features

### 👤 User Features

- User registration and login
- Secure authentication using Spring Security
- Browse the complete book catalogue
- Search books by title or author
- Filter books by category
- Check book availability
- View currently issued books
- Return issued books
- Track book due dates
- View overdue fine records
- Check paid and unpaid fine status
- View book reservations
- Track reservation status
- Contact the library through a query form
- View previously submitted queries
- Track query resolution status
- Personal dashboard for quick access to library services

---

### 🛡️ Administrator Features

- Secure admin dashboard
- Manage the complete book catalogue
- Add new books
- Edit book details
- Delete books
- Manage available book quantity
- View registered users
- Edit user information
- Enable or disable user accounts
- Delete non-admin users
- Protect administrator accounts from destructive actions
- View all currently issued books
- Identify overdue books
- Manage fine records
- Mark fines as paid
- View book reservations
- Fulfill pending reservations
- Review user contact queries
- Mark user queries as resolved
- Monitor important system statistics from the dashboard

---

## 🎨 Frontend Design

The frontend was redesigned with a modern **Library × Academic × Professional** visual style.

The interface uses a calm and professional palette based on:

- Deep Emerald
- Dark Emerald
- Warm Amber
- Warm Ivory
- White
- Semantic success, warning, and danger colors

The UI includes:

- Responsive navigation
- Modern dashboards
- Book catalogue cards
- Professional forms
- Responsive data tables
- Status badges
- Alerts and feedback messages
- Empty states
- Admin management screens
- Mobile-friendly layouts
- Accessible focus states
- Consistent spacing and typography

---

## 🛠️ Technology Stack

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- Spring Data based application architecture

### Frontend

- HTML5
- CSS3
- Thymeleaf Templates
- JavaScript where required

### Security

- Spring Security authentication
- Role-based access control
- USER and ADMIN roles
- CSRF protection for protected form actions

---

## 👥 User Roles

The application supports two primary roles.

### USER

A regular library member can:

- Browse books
- View issued books
- Return books
- View fines
- View reservations
- Contact the library
- Track submitted queries

### ADMIN

An administrator can:

- Manage books
- Manage users
- Monitor issued books
- Manage fines
- Manage reservations
- Manage contact queries
- Access administrative statistics

---

## 📂 Main Frontend Pages

```text
src/main/resources/

├── static/
│   └── css/
│       └── style.css
│
└── templates/
    ├── home.html
    ├── login.html
    ├── register.html
    ├── dashboard.html
    ├── books.html
    ├── my-books.html
    ├── fines.html
    ├── reservations.html
    ├── contact.html
    ├── my-queries.html
    │
    ├── admin-books.html
    ├── admin-book-form.html
    ├── admin-users.html
    ├── admin-user-edit.html
    ├── admin-issued-books.html
    ├── admin-fines.html
    ├── admin-reservations.html
    └── admin-contact.html
```

---

## 📚 Main Modules

### Book Management

Administrators can add, edit, delete, and manage library books.  
Users can browse the catalogue, search for books, filter by category, and check availability.

### Issue & Return Management

Users can view their currently issued books and return them.  
Administrators can monitor all active issue records and identify overdue books.

### Fine Management

The system tracks fine records related to overdue borrowing.

Users can view:

- Fine amount
- Overdue days
- Payment status

Administrators can review fine records and mark unpaid fines as paid.

### Reservation Management

Users can view their book reservations and track whether a reservation is waiting or fulfilled.

Administrators can review reservation requests and mark them as fulfilled.

### User Management

Administrators can:

- View registered users
- Edit user details
- Change roles
- Enable or disable accounts
- Delete eligible user accounts

Administrator accounts are protected from destructive user-management actions.

### Contact & Query Management

Users can submit queries to the library and track their status.

Administrators can review submitted queries and mark them as resolved.

---

## 🔐 Security Features

The project uses Spring Security to provide:

- Authenticated access
- Role-based authorization
- USER and ADMIN access separation
- Protected administrative routes
- CSRF-protected POST requests
- Secure login and logout workflow

---

## 🚀 Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/Thubokar/OBISIP.git
cd OBISIP/Task_2_Library_Management_System
```

### 2. Open the project

Open the project in your preferred Java IDE, such as:

- IntelliJ IDEA
- Eclipse
- Spring Tool Suite
- VS Code with Java extensions

### 3. Configure the application

Configure the application's database and Spring Boot properties according to your local environment.

The configuration is normally located inside:

```text
src/main/resources/application.properties
```

### 4. Run the Spring Boot application

Run the main Spring Boot application class from your IDE.

After the application starts, open:

```text
http://localhost:8080
```

---

## 🖥️ Application Screens

The project contains dedicated screens for:

- Home
- Login
- Registration
- User Dashboard
- Book Catalogue
- My Books
- My Fines
- My Reservations
- Contact Library
- My Queries
- Admin Book Management
- Admin User Management
- Admin Issued Books
- Admin Fine Management
- Admin Reservation Management
- Admin Contact Query Management

---

## 📸 Screenshots

You can add screenshots of the application here after uploading them to a folder such as:

```text
screenshots/
```

Example:

```markdown
![Home Page](screenshots/home.png)
![Dashboard](screenshots/dashboard.png)
![Book Catalogue](screenshots/books.png)
![Admin Dashboard](screenshots/admin-dashboard.png)
```

---

## 🌟 Project Highlights

- Complete user and administrator workflow
- Professional responsive interface
- Role-based access control
- Secure form handling with CSRF protection
- Book inventory management
- Issue and return tracking
- Fine calculation workflow
- Reservation management
- User account administration
- Library support query management
- Reusable global CSS design system
- Spring Boot and Thymeleaf integration

---

## 🔮 Future Improvements

Possible future enhancements include:

- Online fine payments
- Email notifications for due dates
- Reservation availability notifications
- Book cover image uploads
- Advanced catalogue filters
- Pagination
- Sorting
- User profile management
- Library analytics and reports
- Export reports to PDF or Excel
- REST API support
- Barcode or QR-based book issuing

---

## 🎯 Project Purpose

The purpose of this project is to provide a centralized digital system that simplifies library operations for both members and administrators.

It demonstrates practical implementation of:

- Java web development
- Spring Boot
- MVC architecture
- Authentication and authorization
- Thymeleaf server-side rendering
- CRUD operations
- Role-based workflows
- Responsive frontend design

---

## 👨‍💻 Author

**Yash**

Engineering Graduate

Developed as part of an **OASIS project**.

---

## 📄 License

This project is intended for educational and learning purposes.

---

## ⭐ Support

If you found this project useful, consider giving the repository a **star ⭐** on GitHub.
