package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SachDNDTO {
    private String isbn;
    private String tenSach;
    private Integer giaNhap;
    private String tenAnh;
    private Integer soLuong;
    private Integer soLuongNhap;
}
