package com.BookStore.AuthenticationService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NHANVIEN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {
    @Id
    @Column(name = "MANHANVIEN")
    private String maNhanVien;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "HO")
    private String ho;
    @Column(name = "TEN")
    private String ten;
    @Column(name = "GIOITINH")
    private Boolean gioiTinh;
    @Column(name = "DIACHI")
    private String diaChi;
    @Column(name = "NGAYSINH")
    private String ngaySinh;
    @Column(name = "SODIENTHOAI")
    private String soDienThoai;
    @Column(name = "HINHANH")
    private String hinhAnh;
    @Column(name = "SOBHXH")
    private String soBHXH;
}
