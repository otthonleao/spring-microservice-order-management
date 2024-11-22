package dev.otthon.ordermanagement.controllers.dto;

import java.util.List;
import java.util.Map;

public record ApiResponseDTO<T>(Map<String, Object> summary,
                                List<T> data,
                                PaginationResponseDTO pagination) {
}
