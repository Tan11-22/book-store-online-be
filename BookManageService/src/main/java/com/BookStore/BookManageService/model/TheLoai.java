package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "THELOAI")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheLoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTHELOAI")
    private Integer idTacGia;

    @Column(name="TENTHELOAI")
    private String tenTheLoai;

}