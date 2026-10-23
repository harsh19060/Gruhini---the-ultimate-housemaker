# Grihini — The Ultimate Housemaker

A React + TypeScript + Vite frontend prototype for the Household Consumables Inventory and Expense Management System with Intelligent Stock Depletion Prediction.

## What's included

- Overview dashboard with household inventory, spending, low-stock and shopping summaries
- Inventory table with categories and actual-stock observations
- Purchases and expense screens
- Editable shopping list with completion controls
- Smart Input: manual purchase form, barcode lookup demo, receipt-upload preview and WhatsApp/NLU preview
- Stock prediction page (sample estimates clearly labeled as demo)
- ESP32 + HX711 smart-scale screen with simulated sensor readings and pending-review workflow
- Settings for demo mode, appearance and API base URL
- Demo changes persisted in browser `localStorage`
- Isolated API request wrapper in `src/services.ts`

## Important current status

The UI is interactive and runs independently in **Demo Mode**. Demo transactions update browser-local sample data only. Receipt OCR, barcode decoding, WhatsApp/Twilio integration, live ESP32 ingestion, prediction service and Spring Boot API calls are interface placeholders until connected to the matching backend endpoints. Do not present simulated results as live service output.

## Run on Windows 11

1. Install Node.js LTS (the current Vite version requires a recent Node.js release; see https://vite.dev/guide/).
2. Extract the project folder.
3. Open the folder in VS Code.
4. Open Terminal → New Terminal.
5. Run:

   ```powershell
   npm install
   npm run dev
   ```

6. Open the local URL printed by Vite, normally `http://localhost:5173`.
7. For a production build, run `npm run build`; the output is written to `dist/`.

## Backend integration

The current API contracts are not assumed. Once the Spring Boot controllers are agreed, set `VITE_API_BASE_URL` in a `.env` file (copy `.env.example`) and implement the matching endpoints in `src/services.ts` and the page handlers. The sample API base URL is `http://localhost:8080`.

A sensible integration order is:

1. `GET /api/v1/health` (or the backend's actual health endpoint)
2. Products and inventory reads
3. Purchase creation (with backend atomic inventory + expense update)
4. Stock observations
5. Shopping list
6. Python OCR/NLU/prediction service calls through Spring Boot
7. Sensor-reading endpoint

Do not switch the UI to live mode until the endpoint paths and request/response DTOs actually match the backend.
