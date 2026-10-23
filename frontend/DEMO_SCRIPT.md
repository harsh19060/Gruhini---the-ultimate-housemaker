# 5-minute presentation demo

**Say first:** “This is our Grihini frontend prototype. The interface is interactive in Demo Mode; live OCR/NLU/backend/sensor integrations are still being connected. The sample values are clearly labeled so we don't misrepresent mocked data as live output.”

## Suggested walkthrough

1. **Overview** — point out inventory value, monthly spending, low-stock count, recent purchases, and the example insight.
2. **Inventory** — filter by category, then use the three-dot action on a product to record the physical stock quantity. Explain that this creates a stock observation; it does not prove consumption.
3. **Add purchase** — return to Overview and click `Add purchase`. Enter a product, quantity, unit, amount, and category. Confirm. Show the new purchase and the increased stock / spending totals.
4. **Smart input** — show the four planned input channels. For receipt upload, choose any sample image and click `Extract receipt items`; clearly call the rows a demo extraction preview. For WhatsApp, click `Parse message` and then approve the sample item.
5. **Shopping list** — add an item or mark one as complete. Explain that prediction suggestions must be accepted before being placed on the list.
6. **Stock predictions** — explain the planned pipeline: purchase history + manual/WhatsApp/sensor observations → account for intervening purchases → estimate a consumption rate → predict depletion interval and confidence. Emphasize that the displayed figures are illustrative, not the trained model's output.
7. **Smart scale** — click `Simulate reading` to create a pending ESP32-style observation, then approve or reject it. Explain that this button simulates a request; it does not connect to the physical ESP32.
8. **Settings** — point out the explicit integration-status list and the saved Spring Boot base-URL field.

## Before presenting

- Run `npm install`, then `npm run dev`.
- Open the printed `localhost` URL in Chrome.
- Refresh once and confirm that demo changes persist (localStorage).
- Avoid switching off Demo Mode; live API wiring is not yet active.
- Keep backend and hardware demos separate unless they are actually connected.
