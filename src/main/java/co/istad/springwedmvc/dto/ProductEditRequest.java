package co.istad.springwedmvc.dto;

public record ProductEditRequest(
        String name,
        Double price
) {
}
