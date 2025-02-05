package co.istad.springwedmvc.dto;

import java.time.LocalDateTime;

public record ProductDto(
        Integer id,
        String uuid,
        String name,
        Double price,
        Integer qty,
        LocalDateTime importedDate,
        Boolean status
) {}
