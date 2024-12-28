package com.BookStore.BookManageService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTDMSDTO {
    private String ISBN;
    private String tenSach;
    private String tenAnh;
    private Integer soLuong;
    private Integer donGia;
}
