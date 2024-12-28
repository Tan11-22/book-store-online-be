package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoanhThuDTO {
    private Integer thang;
    private Integer nam;
    private Long tongSoSachBan;
    private Long tongSoSachNhap;
    private Long thanhTienBan;
    private Long thanhTienNhap;
}
