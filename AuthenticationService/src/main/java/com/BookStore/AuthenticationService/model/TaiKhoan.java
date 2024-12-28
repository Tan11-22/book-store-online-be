package com.BookStore.AuthenticationService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TAIKHOAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoan {
    @Id
    @Column(name = "TENDANGNHAP")
    private String tenDangNhap;
    @Column(name = "MATKHAU")
    private String matKhau;
    @Column(name = "TRANGTHAI")
    private Boolean trangThai;
    @ManyToOne
    @JoinColumn(name = "IDQUYEN")
    private Quyen quyen;
}
