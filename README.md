# 🚗 Ride Management Console App (Java OOP Project)

This is a **console-based ride management system** built in Java as a practice project to apply Object-Oriented Programming (OOP) and simple system design principles.

## 🎯 Project Goals

- Practice core **OOP concepts** (Classes, Encapsulation, Abstraction, Inheritance)
- Apply clean code and **layered architecture**
- Simulate a basic ride-booking platform with users, rides, and ratings

---

## 📦 Features

- 👤 **User Management**
  - Add new passengers and drivers
  - View all users by type
  - Fetch user details by ID
  - View total counts for passengers and drivers

- 🚕 **Ride Management**
  - Create a ride (assign driver & passenger)
  - Start, End, or Cancel a ride
  - View all created rides

- ⭐ **Driver Rating System**
  - Rate a driver after completing a ride
  - View average rating for each driver

---

## 📁 Project Structure
```
src/
├── app/                      # Main application logic
│   └── RideApp.java          # Console interface and user interaction

├── models/                   # Domain models
│   ├── Driver.java
│   ├── Passenger.java
│   ├── Rate.java
│   ├── Ride.java
│   ├── RideStatus.java       # Enum for ride states
│   └── User.java             # Base class for Driver & Passenger

├── service/                  # Business logic layer
│   ├── RateService.java      # Handles rating operations
│   └── RideService.java      # Handles ride operations

├── storage/                  # Data storage (in-memory)
│   └── UsersDataStorage.java # Stores and manages users

├── utils/                    # Utility classes
│   ├── Fare.java             # Placeholder for fare calculation logic      
│   └── LoggerUtil.java       # Logger setup utility

└── Main.java                 # Entry point       

.gitignore                    # Git ignore rules
Ride Sharing System.iml       # IntelliJ project config
```

---

## 🛠️ Technologies Used

- Java 17
- Object-Oriented Programming
- Layered Design (App → Service → Storage)
- Java Logging (with custom LoggerUtil)

---

## 🧠 Concepts Practiced

- OOP Design Principles
- Clean code separation
- Basic system flow & user interaction
- Input validation and logging

---

## 🤓 Author

👨‍💻 Built with 💙 as part of my learning journey in Java & backend development.

If you found this project useful or inspiring, feel free to ⭐ the repo!

---

