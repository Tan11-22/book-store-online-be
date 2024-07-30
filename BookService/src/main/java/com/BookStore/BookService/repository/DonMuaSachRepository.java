package com.BookStore.BookService.repository;

import com.BookStore.BookService.model.DonMuaSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface DonMuaSachRepository extends JpaRepository<DonMuaSach, Integer> {
    @Procedure(procedureName = "SP_THEM_DON_MUA_SACH")
    void themDonMuaSach(
            @Param("tenDangNhap") String tenDangNhap,
            @Param("trangthai") Integer trangThai,
            @Param("diachigiao") String diaChiGiao
    );

    @Procedure(procedureName = "SP_THEM_SACH_VAO_CT_DON_MUA")
    void themSachVaoCTDonMua(
            @Param("isbn") String isbn,
            @Param("tenDangNhap") String tenDangNhap,
            @Param("soluong") Integer soLuong,
            @Param("dongia") Integer donGia
    );

    @Procedure(procedureName = "SP_XOA_CT_DMS")
    void xoaCTDonMuaSach(
            @Param("iddon") Integer idDon
    );

    @Procedure(procedureName = "SP_XOA_DMS")
    void xoaDonMuaSach(
            @Param("iddon") Integer idDon
    );
}
