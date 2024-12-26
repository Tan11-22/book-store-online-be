package com.BookStore.BookService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "DONMUASACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonMuaSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDONMUASACH")
    private Integer idDonMuaSach;
    @Column(name="TENDANGNHAP")
    private String tenDangNhap;
    @Column(name = "NGAYMUA")
    private String ngayMua;
    @Column(name = "TRANGTHAITHANHTOAN")
    private Integer trangThai;
    @Column(name = "NGAYTHANHTOAN")
    private String ngayThanhToan;
    @Column(name = "DIACHIGIAO")
    private String diaChiGiao;
    @Column(name = "PHIVANCHUYEN")
    private Integer phiVanChuyen;
    @Column(name = "SDTNGUOINHAN")
    private String sdtNguoiNhan;
    @Column(name = "MANVDUYET")
    private String manvDuyet;
    @Column(name = "MANVGIAO")
    private String manvGiao;
    @Column(name = "TRANGTHAIDONHANG")
    private Integer trangThai1;
}
