package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CTDONNHAPSACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTDonNhapSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTDONNHAPSACH")
    private Integer idCTDonNhapSach;
    @Column(name = "IDDONNHAPSACH")
    private Integer idDonNhapSach;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "SOLUONG")
    private Integer soLuong;
    @Column(name = "DONGIA")
    private Integer donGia;
}
