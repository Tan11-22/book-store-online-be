package com.BookStore.BookManageService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonMuaSachDTO {
    private Integer idDonMuaSach;
    private String hoTen;
    private String sdt;
    private String email;
    private Date ngayMua;
    private Integer trangThaiThanhToan;
    private String diaChiGiao;
    private Integer phiVanChuyen;
    private Integer trangThaiDonHang;
}
