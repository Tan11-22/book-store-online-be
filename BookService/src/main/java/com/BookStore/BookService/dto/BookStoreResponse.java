package com.BookStore.BookService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookStoreResponse<T> {
    private Integer code;
    private String status;
    private T data;
}
