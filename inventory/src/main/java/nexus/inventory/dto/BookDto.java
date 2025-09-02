package nexus.inventory.dto;

import java.math.BigInteger;
import java.util.List;

public record BookDto(
    String title,
    String autor,
    String description,
    String publisher,
    Integer publishYear,
    BigInteger price,
    Long stock,
    List<Integer> listCodeCategory,
    Long bookCode
) {}
