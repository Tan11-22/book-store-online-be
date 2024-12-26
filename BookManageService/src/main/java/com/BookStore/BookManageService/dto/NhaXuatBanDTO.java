package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhaXuatBanDTO {
    private String maNhaXuatBan;
    private String tenNhaXuatBan;
    private String chuSoHuu;
    private Integer soSach;
}
