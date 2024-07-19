package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THELOAISACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheLoaiSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTHELOAISACH")
    private Integer idTacGia;

    @Column(name="IDTHELOAI")
    private Integer tenTheLoai;

    @Column(name="ISBN")
    private String isbn;

}