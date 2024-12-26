package com.BookStore.BookManageService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "DIACHIGIAO")
    private String diaChiGiao;
    @Column(name = "MANVDUYET")
    private String manvDuyet;
    @Column(name = "MANVGIAO")
    private String manvGiao;
    @Column(name = "TRANGTHAIDONHANG")
    private Integer trangThai1;
}