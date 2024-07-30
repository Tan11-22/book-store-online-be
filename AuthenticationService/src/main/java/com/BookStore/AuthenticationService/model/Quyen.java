package com.BookStore.AuthenticationService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "QUYEN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDQUYEN", nullable = false)
    private Integer idQuyen;

    @Column(name = "TENQUYEN")
    private String tenQuyen;

    @OneToMany(mappedBy = "quyen", fetch = FetchType.LAZY)
    private List<TaiKhoan> taiKhoan;
}
