package com.BookStore.BookService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardSach {
    private String isbn;
    private String tenSach;
    private String tenTacGia;
    private Integer giaBan;
    private Integer giaGiam;
    private String tenAnh;
    private Integer soLuong;
}
