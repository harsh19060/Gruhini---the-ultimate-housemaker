# Gruhini — Smart Household Consumables Management System

**A smart inventory management system for everyday household essentials.**

Gruhini helps users track household stock, record purchases and expenses, monitor consumption, and manage shopping lists. The project is designed to support future IoT-based inventory tracking using RFID and weight sensors.

---

## ✨ Features

- 📦 **Inventory Management** — Add, update, search, and delete household items.
- 📊 **Dashboard** — View inventory summaries, recent spending, and stock valuation.
- 🛒 **Purchase Tracking** — Record purchases and update item stock.
- 💰 **Expense Management** — Maintain household expense records.
- ⚖️ **Consumption Tracking** — Record consumption events using weight measurements.
- 🚨 **Low-Stock Monitoring** — Identify items that may need replenishment.
- 📝 **Shopping List** — Add items, update completion status, and remove entries.
- 🏷️ **Barcode Lookup** — Find inventory items using their barcode.
- 📡 **IoT-Ready Design** — Backend endpoint prepared for hardware-based consumption reporting.

## 🧰 Tech Stack

| Component | Technology |
|---|---|
| Backend | Java 17, Spring Boot |
| API | Spring Web, REST |
| Database | MySQL |
| Persistence | Spring Data JPA |
| Frontend | React, Vite |
| Hardware (planned/integration work) | ESP32, HX711 load cell amplifier, 5 kg load cell, RC522 RFID reader |
| Version Control | Git and GitHub |

## 🏗️ Project Structure

```text
household-consumables/
├── backend/
│   ├── src/main/java/
│   │   └── com/household/app/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── repository/
│   │       └── service/
│   ├── src/main/resources/
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── main.jsx
│   │   └── style.css
│   ├── index.html
│   └── package.json
├── .gitignore
└── README.md
```

## ⚙️ Prerequisites

Install the following before running the project:

- Java JDK 17
- Maven
- MySQL Server
- Node.js and npm
- Git

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/harsh19060/Gruhini---the-ultimate-housemaker.git
cd Gruhini---the-ultimate-housemaker
```

### 2. Configure MySQL

Create the database:

```sql
CREATE DATABASE household_consumables;
```

Configure the database connection in your local Spring Boot configuration. Set your MySQL username and password using your own local credentials.

**Security:** Never commit database passwords, API keys, or other secrets to GitHub.

### 3. Run the backend

Open a terminal in the `backend` directory:

```bash
mvn clean compile
mvn spring-boot:run
```

The backend uses port `8080` by default, unless configured otherwise.

### 4. Run the frontend

Open another terminal in the `frontend` directory:

```bash
npm install
npm run dev
```

Open the local URL printed by Vite in the terminal.

## 🔌 API Overview

The backend provides REST endpoints for the following operations:

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/health` | Check backend health |
| GET | `/api/dashboard` | Retrieve dashboard data |
| GET | `/api/items` | List or search inventory items |
| GET | `/api/items/{id}` | Retrieve an item |
| POST | `/api/items` | Create an item |
| PUT | `/api/items/{id}` | Update an item |
| DELETE | `/api/items/{id}` | Delete an item |
| GET | `/api/purchases` | View purchase history |
| POST | `/api/purchases` | Record a purchase |
| GET | `/api/expenses` | View expenses |
| POST | `/api/expenses` | Record an expense |
| GET | `/api/consumption` | View consumption history |
| POST | `/api/hardware/consumption` | Submit a hardware consumption event |
| GET | `/api/shopping-list` | View shopping list |
| POST | `/api/shopping-list` | Add a shopping-list item |
| PATCH | `/api/shopping-list/{id}/toggle` | Toggle item completion |
| DELETE | `/api/shopping-list/{id}` | Remove a shopping-list item |
| GET | `/api/lookup/barcode/{barcode}` | Look up an item by barcode |

Additional endpoints include stock adjustments and item updates. See the backend controller for the complete implementation.

## 📡 IoT Integration — Planned Hardware

Gruhini is designed to support a smart weighing and identification workflow:

1. An RFID reader identifies the household item.
2. A load cell connected through the HX711 measures its weight.
3. The ESP32 processes the weight readings.
4. The device reports a consumption event to the backend.
5. The backend records the event and updates inventory.

Hardware integration depends on the corresponding device firmware and configuration being completed.

## 🔮 Future Enhancements

- Complete ESP32 and RFID hardware integration.
- Automatic low-stock notifications.
- Improved purchase and expense analytics.
- More robust validation and error handling.
- Authentication and user-specific household inventories.
- Automated testing and deployment.

## 🔐 Security Notes

- Keep local credentials outside version control.
- Do not publish real database passwords or personal information.
- Configure production credentials through environment variables or a secure secrets manager.

## 👨‍💻 Author

**Harsh**  
GitHub: [@harsh19060](https://github.com/harsh19060)

## 📄 License

This project is distributed under the MIT License. See the `LICENSE` file for details.
