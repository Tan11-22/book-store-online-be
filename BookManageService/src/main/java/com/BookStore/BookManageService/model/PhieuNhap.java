package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "PHIEUNHAP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOPHIEUNHAP")
    private Integer soPhieuNhap;
    @Column(name="MANHANVIEN")
    private String maNhanVien;
    @Column(name = "IDDON")
    private Integer idDonNhapSach;
    @Column(name="NGAYLAP")
    private String ngayLap;
}
