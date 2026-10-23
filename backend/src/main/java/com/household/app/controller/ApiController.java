
package com.household.app.controller;

import com.household.app.dto.Requests.*;
import com.household.app.entity.*;
import com.household.app.service.InventoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final InventoryService service;

    public ApiController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "ok",
                "app", "household-consumables"
        );
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return service.dashboard();
    }

    @GetMapping("/items")
    public List<Item> items(
            @RequestParam(required = false) String q
    ) {
        return service.allItems(q);
    }

    @GetMapping("/items/{id}")
    public Item item(@PathVariable Long id) {
        return service.item(id);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@Valid @RequestBody ItemRequest request) {
        return service.create(request);
    }

    @PutMapping("/items/{id}")
    public Item update(
            @PathVariable Long id,
            @Valid @RequestBody ItemRequest request
    ) {
        return service.update(id, request);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/items/{id}/stock-adjustments")
    public Item adjust(
            @PathVariable Long id,
            @Valid @RequestBody StockAdjustment request
    ) {
        return service.adjust(id, request);
    }

    @GetMapping("/purchases")
    public List<Purchase> purchases() {
        return service.purchaseHistory();
    }

    @PostMapping("/purchases")
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase purchase(
            @Valid @RequestBody PurchaseRequest request
    ) {
        return service.purchase(request);
    }

    @GetMapping("/expenses")
    public List<Expense> expenses() {
        return service.expenseHistory();
    }

    @PostMapping("/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public Expense expense(
            @Valid @RequestBody ExpenseRequest request
    ) {
        return service.addExpense(request);
    }

    @GetMapping("/consumption")
    public List<ConsumptionEvent> consumption() {
        return service.history();
    }

    @PostMapping("/hardware/consumption")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumptionEvent hardwareConsumption(
            @Valid @RequestBody ConsumptionRequest request
    ) {
        return service.consume(request);
    }

    @GetMapping("/shopping-list")
    public List<ShoppingListItem> shopping() {
        return service.shoppingList();
    }

    @PostMapping("/shopping-list")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListItem addShopping(
            @Valid @RequestBody ShoppingRequest request
    ) {
        return service.addShopping(request);
    }

    @PatchMapping("/shopping-list/{id}/toggle")
    public ShoppingListItem toggle(@PathVariable Long id) {
        return service.toggleShopping(id);
    }

    @DeleteMapping("/shopping-list/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShopping(@PathVariable Long id) {
        service.deleteShopping(id);
    }

    @GetMapping("/lookup/barcode/{barcode}")
    public Item barcode(@PathVariable String barcode) {
        return service.allItems(null)
                .stream()
                .filter(item -> barcode.equals(item.getBarcode()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Barcode not found; add item manually"
                ));
    }
}
