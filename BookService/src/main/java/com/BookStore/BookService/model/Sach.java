package com.BookStore.BookService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sach {
    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "TENSACH")
    private String tenSach;

    @Column(name="KHUONKHO")
    private String khuonKho;

    @Column(name = "SOTRANG")
    private Integer soTrang;

    @Column(name="TRONGLUONG")
    private Integer trongLuong;

    @Column(name = "MOTA")
    private String moTa;

    @Column(name = "SOLUONG")
    private Integer soLuong;


    @Column(name = "MANHAXUATBAN")
    private String maNhaXuatBan;

}
