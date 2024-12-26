package com.BookStore.BookService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BINHLUAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BinhLuanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBINHLUAN")
    private Integer idBinhLuan;
    @Column(name = "TENDANGNHAP")
    private String tenDangNhap;
    @Column(name="ISBN")
    private String isbn;
    @Column(name = "NOIDUNG")
    private String noiDung;
    @Column(name = "DIEM")
    private Integer diem;
    @Column(name = "NGAY")
    private LocalDateTime ngay;
}
