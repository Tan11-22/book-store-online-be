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
    private Date ngayMua;
    @Column(name = "TRANGTHAI")
    private Integer trangThai;
    @Column(name = "DIACHIGIAO")
    private String diaChiGiao;

}
