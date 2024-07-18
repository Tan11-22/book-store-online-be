package com.BookStore.BookService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GIOHANG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDGIOHANG")
    private Integer idTacGia;

    @Column(name="TENDANGNHAP")
    private String tenDangNhap;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name="SOLUONG")
    private Integer soLuong;
}