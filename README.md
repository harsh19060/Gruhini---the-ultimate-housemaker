# HomeStock — Household Consumables Manager

Full-stack starter app for one household: inventory, purchases, expenses, shopping list, consumption history and ESP32 smart-scale API. Stack: Java 21, Spring Boot 3.4, Spring Data JPA/Hibernate, MySQL, React + Vite.

## Requirements
- Java 21+
- Maven 3.9+
- MySQL 8+
- Node.js 20+

## 1. Start MySQL
Create the database (optional; the JDBC URL also has `createDatabaseIfNotExist=true`):

```sql
CREATE DATABASE household_consumables;
```

Set credentials before starting backend. Linux/macOS:

```bash
export DB_USERNAME=root
export DB_PASSWORD='your_mysql_password'
cd backend
mvn spring-boot:run
```

Windows PowerShell:

```powershell
$env:DB_USERNAME='root'
$env:DB_PASSWORD='your_mysql_password'
cd backend
mvn spring-boot:run
```

Backend runs at `http://localhost:8080`. Test `GET http://localhost:8080/api/health`. Hibernate creates tables from JPA entities using `ddl-auto=update` for local development.

## 2. Start React
In a second terminal:

```bash
cd frontend
npm install
npm run dev
```

Open the URL Vite prints (normally `http://localhost:5173`). If backend uses another URL, create `frontend/.env` with `VITE_API_URL=http://localhost:8080/api`.

## REST API
- `GET /api/health`
- `GET /api/dashboard`
- `GET /api/items?q=rice`, `POST /api/items`, `GET /api/items/{id}`, `PUT /api/items/{id}`, `DELETE /api/items/{id}`
- `POST /api/items/{id}/stock-adjustments` — positive quantity adds stock; negative quantity subtracts stock (cannot go below zero)
- `GET/POST /api/purchases` — purchase creates purchase record and adds quantity to stock
- `GET/POST /api/expenses`
- `GET /api/consumption`
- `POST /api/hardware/consumption` — JSON example below
- `GET/POST /api/shopping-list`, `PATCH /api/shopping-list/{id}/toggle`, `DELETE /api/shopping-list/{id}`
- `GET /api/lookup/barcode/{barcode}`

### ESP32 consumption request
```json
{
  "rfidUid": "F7:FC:51:63",
  "initialWeightGrams": 1000,
  "finalWeightGrams": 750,
  "eventKey": "esp32-session-unique-id"
}
```
Backend maps known RFID UID to an item, calculates `initial-final`, checks available stock, and updates stock + event in one transaction. `eventKey` must be unique per completed session; retrying the same key is rejected to avoid double deductions. Final weight greater than or equal to initial weight is rejected as non-consumption/refill.

Known starter mappings: `F7:FC:51:63` Sugar, `60:7D:08:55` Tea, `BC:CA:7E:09` Rice. Edit item RFID UID in inventory if needed. RFID UID normalization is uppercase.

## Current scope and honest limitations
- Manual inventory, purchase, expense and shopping-list flows are implemented.
- Dashboard shows inventory, low-stock count, recent recorded spend and estimated stock value. Spend currently sums the latest 100 purchases and latest 100 expenses, not a date-filtered monthly report.
- Barcode can be typed or scanned by a USB/Bluetooth scanner that behaves like a keyboard. A camera-based scanner UI is not included yet.
- Receipt OCR and WhatsApp are documented integration points, not live integrations; provider credentials and parsing/confirmation workflow are not configured.
- No login is included because this is the requested single-household local app. Do not expose it to the public internet as-is.
- `ddl-auto=update` is for development only. Use Flyway/Liquibase migrations, backups, authentication and API protection before production deployment.
- For smart-scale accuracy, calibrate HX711, tare correctly, ensure load is routed through the load cell, and send a unique event only after the RFID session has been validated. Current ESP32 firmware still needs Wi-Fi HTTP client code to post to the endpoint.
