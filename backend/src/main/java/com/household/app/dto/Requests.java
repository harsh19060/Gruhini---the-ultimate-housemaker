
package com.household.app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Requests {

    public record ItemRequest(
            @NotBlank String name,
            String category,
            String unit,
            @NotNull @DecimalMin("0.0") BigDecimal stock,
            @NotNull @DecimalMin("0.0") BigDecimal lowStockThreshold,
            @DecimalMin("0.0") BigDecimal averageUnitPrice,
            String rfidUid,
            String barcode
    ) {}

    public record StockAdjustment(
            @NotNull BigDecimal quantity,
            String reason
    ) {}

    public record PurchaseRequest(
            @NotNull Long itemId,
            @NotNull @DecimalMin("0.001") BigDecimal quantity,
            @NotNull @DecimalMin("0.0") BigDecimal totalPrice,
            String storeName
    ) {}

    public record ExpenseRequest(
            @NotBlank String description,
            String category,
            @NotNull @DecimalMin("0.0") BigDecimal amount
    ) {}

    public record ShoppingRequest(
            Long itemId,
            @NotBlank String name,
            @NotNull @DecimalMin("0.001") BigDecimal quantity,
            String unit
    ) {}

    public record ConsumptionRequest(
            @NotBlank String rfidUid,
            @NotNull @DecimalMin("0.0") BigDecimal initialWeightGrams,
            @NotNull @DecimalMin("0.0") BigDecimal finalWeightGrams,
            @NotBlank String eventKey
    ) {}
}
