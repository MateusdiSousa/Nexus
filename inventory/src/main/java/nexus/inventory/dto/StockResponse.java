package nexus.inventory.dto;

public record StockResponse(
    Long quatity,
    Long bookCode,
    Long newStockLevel
) {}
