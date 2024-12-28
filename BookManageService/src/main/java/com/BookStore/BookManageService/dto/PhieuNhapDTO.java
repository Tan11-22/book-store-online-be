package com.BookStore.BookManageService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhieuNhapDTO {

    private Integer soPhieuNhap;
    private String maNhanVien;
    private Integer idDonNhapSach;
    private String ngayLap;
    private String tenNhanVien;
}
