# Client-Server Application for Library Management (Seminarski PS)

## 📌 About the Project
This is a Java client-server application developed for a library management system.  
The system allows managing readers, categories, and borrowing books through a desktop client application.

The application is divided into three parts:

- **ClientApp** – GUI application used by the user
- **ServerApp** – handles business logic and database communication
- **CommonApp** – shared domain classes used by both client and server

Communication between client and server is done using sockets.

---

## ✨ Features
- User login system
- Manage readers (add, update, delete)
- Manage book categories
- Borrowing management

## 🔄 System Architecture
The application follows a client-server architecture.
The client communicates with the server using sockets.  
The server processes requests and interacts with the MySQL database.

---

## ⚙️ Technologies Used
- Java
- Socket programming
- JDBC (MySQL)
- NetBeans GUI (Swing)
- MVC architecture

---

## 🗄️ Database Setup
The application uses a local MySQL database.

1. Create a database in MySQL (e.g. `library`)
2. Run the SQL script provided in `database.sql` (if available)
3. Update database credentials in ServerApp configuration

---

## 🚀 How to Run the Project

### 1. Start Server
Run the `ServerApp` project first.

### 2. Start Client
Run the `ClientApp` project.

---

## 📁 Project Structure
- ClientApp – user interface
- ServerApp – backend logic and database communication
- CommonApp – shared domain model

---

## 👨‍💻 Author
Luka Adžić