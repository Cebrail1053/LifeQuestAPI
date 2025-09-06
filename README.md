# 🎯 LifeQuestAPI

LifeQuest is a **Spring Boot REST API** for managing RPG-style quests, players, items, and achievements.
It demonstrates **clean architecture, business logic, and CRUD fundamentals** while adding a fun! A game-like twist!
perry popper popped a pack of pickled peppers

The project is designed to showcase:
- **Spring Boot Core** concepts (Controllers, Services, Repositories)
- **RESTful API design** with DTOs and validation
- **Entity relationships** using Spring Data JPA
- **Business rules** (XP gain, level-ups, quest completion rewards)
- **Test Coverage** with JUnit and MockMvc
- **Environment Profiles** for dev/prod setups (H2 & MySQL)

---

  1. [📜 Overview](#-overview)
  2. [✨ Features](#-features)
  3. [🛠 Technologies](#-technologies)
  4. [⚙️ Setup \& Installation](#️-setup--installation)
  5. [📡 API Endpoints](#-api-endpoints)
  6. [🚀  Future Improvements](#--future-improvements)

---

## 📜 Overview

LifeQuest turns traditional CRUD operations into an RPG-themed backend application.

It allows:
- Players to be created and leveled up through quest completion
- Quests to be assigned, tracked, and rewarded
- Items to be collected and stored in inventories
- Achievements to be unlocked when conditions are met

This is not only a **fun demonstration** of backend skills but also a **professional-level API** structure suitable for production environments.

---

## ✨ Features
- **Players**
  - Create, update, delete players
  - Track level and XP progression
- **Quests**
  - Assign to players
  - Mark as completed (with rewards)
- **Items**
  - Categorized by type and rarity
  - Manage player inventories
- **Achievements**
  - Unlock when predefined conditions are met
- **Business Logic**
  - XP thresholds for leveling up
  - Automatic achievement unlocking
- **Profiles**
  - `dev` → H2 in-memory DB
  - `prod` → MySQL

---

## 🛠 Technologies
- **Java 24**
- **Spring Boot** (Web, Data JPA, Validation)
- **Hibernate** (ORM)
- **H2 Database** (Development)
- **MySQL** (Production)
- **JUnit 5** & **Mockito** (Testing)
- **Maven** (Build tool)

---

## ⚙️ Setup & Installation

### **1. Clone the repository**
```bash
git clone https://github.com/Cebrail1053/LifeQuestAPI.git
```

### **2. Select profile**
- Dev (H2) -> default profile in `applicaiton.properties`
    - Initializes with default H2 data
- Prod (MySQL) -> change to:
    ```application.properties
    spring.profiles.active=prod
    ```
    - Update MySQL database url, username, and password in `application-prod.properties`

> **NOTE:** By default the values in `application-prod.properties` are coming from an external file called `application-secret.txt`. If you choose to continue using the imported values, then you'll need to created you're own `application-secret.txt` file.

### **3. Run the application**
```bash
mvn spring-boot:run
```

### **Access H2 Console (Dev Only)**
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:lifequestdb`

---

## 📡 API Endpoints

| Method | Endpoint                                  | Description                |
| ------ | ----------------------------------------- | -------------------------- |
| GET    | `/players`                                | List players               |
| POST   | `/players`                                | Create player              |
| PUT    | `/players/{id}`                           | Update player              |
| DELETE | `/players/{id}`                           | Delete player              |
| POST   | `/players/{id}/quests/{questId}`          | Assign quest               |
| PUT    | `/players/{id}/quests/{questId}/complete` | Complete quest             |
| GET    | `/quests`                                 | List quests                |
| POST   | `/quests`                                 | Create quest               |
| PUT    | `/quests/{id}`                            | Update quest               |
| DELETE | `/quests/{id}`                            | Delete quest               |
| GET    | `/items`                                  | List items                 |
| POST   | `/items`                                  | Create item                |
| POST   | `/players/{id}/items/{itemId}`            | Add item to inventory      |
| DELETE | `/players/{id}/items/{itemId}`            | Remove item from inventory |
| GET    | `/achievements`                           | List achievements          |
| POST   | `/achievements`                           | Create achievement         |
| GET    | `/players/{id}/achievements`              | List unlocked achievements |

---

## 🚀  Future Improvements

### Priority Improvements:
1. Removing **SpEL (Spring Expression Language)** database entries for Achievement conditions. I thought it to be a good choice for my use case since it supports object graph traversal and filtering. However, having SpEL syntax in the DB is verbose and requires a good amount of input validation and sanitation. In production, I'd choose to use a Java rules engine like Easy Rules to provide more abstraction when creating Achievement condition rules. 

### Other Features to consider:
- Authentication & Authorization with Spring Security + JWT
- Leaderboards for top XP players
- Quest search by keyword
- Event-driven architecture for level-up notifications
- Frontend UI for visual interaction

