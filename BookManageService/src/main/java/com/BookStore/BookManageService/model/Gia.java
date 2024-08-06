package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "GIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDGIA")
    private Integer idGia;
    @Column(name="TENGIA")
    private String tenGia;
}
