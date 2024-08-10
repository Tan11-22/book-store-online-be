package com.BookStore.BookManageService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonNhapSachDTO {
    private Integer idDonNhapSach;
    private String maNhaXuatBan;
    private String maNhanVien;
    private Date ngayDat;
    private String tenNhaXuatBan;
    private Integer daNhap;
}
