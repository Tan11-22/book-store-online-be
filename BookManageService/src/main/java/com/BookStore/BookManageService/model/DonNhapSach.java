package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "DONNHAPSACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonNhapSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDONNHAPSACH")
    private Integer idDonNhapSach;
    @Column(name = "MANHAXUATBAN")
    private String maNhaXuatBan;
    @Column(name = "MANHANVIEN")
    private String maNhanVien;
    @Column(name = "NGAYDAT")
    private Date ngayDat;
}
