package com.BookStore.BookService.repository;

import com.BookStore.BookService.model.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface GioHangRepository extends JpaRepository<GioHang, Integer> {
    @Procedure(procedureName = "SP_THEM_SACH_VAO_GIO_HANG")
    void themSachVaoGioHang(
            @Param("tendangnhap") String tenDangNhap,
            @Param("isbn") String isbn,
            @Param("soluong") Integer soLuong
    );

    @Procedure(procedureName = "SP_CAP_NHAT_SACH_TRONG_GIO_HANG")
    void capNhapSachTrongGioHang(
            @Param("id") Integer id,
            @Param("soluong") Integer soLuong
    );

    @Procedure(procedureName = "SP_XOA_SACH_TRONG_GIO_HANG")
    void xoaSachTrongGioHang(
            @Param("id") Integer id
    );
}
