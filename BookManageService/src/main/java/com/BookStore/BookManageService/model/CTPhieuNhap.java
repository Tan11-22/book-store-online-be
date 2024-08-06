package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CTPHIEUNHAP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTPhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTPHIEUNHAP")
    private Integer idCTPhieuNhap;
    @Column(name = "SOPHIEUNHAP")
    private Integer soPhieuNhap;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name="SOLUONG")
    private Integer soLuong;
    @Column(name = "DONGIA")
    private Integer donGia;
}
