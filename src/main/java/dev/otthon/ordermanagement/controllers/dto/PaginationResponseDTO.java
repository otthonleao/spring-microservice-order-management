package dev.otthon.ordermanagement.controllers.dto;

public record PaginationResponseDTO(Integer page,
                                   Integer pageSize,
                                   Integer totalElements,
                                   Integer totalPages) {
}
