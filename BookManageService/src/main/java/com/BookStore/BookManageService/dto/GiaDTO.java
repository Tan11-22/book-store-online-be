package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiaDTO {
    private String isbn;
    private String tenSach;
    private String tenAnh;
    private Integer giaNhap;
    private Integer giaBan;
    private Integer giaKhuyenMai;
}
