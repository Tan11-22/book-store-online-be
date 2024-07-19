package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TACGIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TacGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTACGIA")
    private Integer idTacGia;

    @Column(name="HO")
    private String ho;

    @Column(name = "TEN")
    private String ten;
}
