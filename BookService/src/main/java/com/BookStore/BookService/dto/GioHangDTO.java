package com.BookStore.BookService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangDTO {
    private Integer idGioHang;
    private String tenSach;
    private Integer soLuong;
    private Integer giaBan;
    private Integer giaGiam;
    private String anh;
}
