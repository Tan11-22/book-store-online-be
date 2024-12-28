package com.BookStore.BookService.repository;

import com.BookStore.BookService.model.DonMuaSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DonMuaSachRepository extends JpaRepository<DonMuaSach, Integer> {
    @Procedure(procedureName = "SP_THEM_DON_MUA_SACH")
    void themDonMuaSach(
            @Param("tenDangNhap") String tenDangNhap,
            @Param("trangthai") Integer trangThai,
            @Param("diachigiao") String diaChiGiao,
            @Param("phi") Integer phi,
            @Param("sdt") String sdt
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

    @Query(value = "SELECT SOLUONG FROM SACH WHERE ISBN=:isbn", nativeQuery = true)
    int laySoLuongSachDangCo(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_CHI_TIET_DON_MUA_HANG(:idDonMuaSach)}", nativeQuery = true)
    List<Map<String, Object>> layChiTietDonMuaHang(@Param("idDonMuaSach") int idDonMuaSach);

    @Query(value = "{call SP_LAY_DON_MUA_HANG(:tdn)}", nativeQuery = true)
    List<DonMuaSach> layDonMuaHang(@Param("tdn") String tenDangNhap);


    @Procedure(procedureName = "SP_UPDATE_SL_SACH")
    void updateSoLuongSach(
            @Param("isbn") String isbn,
            @Param("soLuong") int soLuong);

    @Query(value = "SELECT SOLUONG FROM SACH WHERE ISBN=:isbn",nativeQuery = true)
    int getSoLuongSach(@Param("isbn") String isbn);


    @Query(value = "{call SP_DON_MUA_SACH_MOI_NHAT(:tdn)}",nativeQuery = true)
    int getIdDonMuaSach(@Param("tdn") String tenDangNhap);


    @Procedure(procedureName = "SP_CAP_NHAT_TRANG_THAI_DON_MUA")
    void updateTrangThaiDonMua(
            @Param("idDonMua") int idDon,
            @Param("tt") int trangThai);


    @Query(value = "{call SP_GET_ISBN_BY_ID_DM(:idDonMua)}",nativeQuery = true)
    List<Map<String, Object>> getISBNbyIdDon(@Param("idDonMua") int idDonMua);


    @Procedure(procedureName = "SP_XOA_SACH_GIO_HANG")
    void xoaSachTrongGioHang(
            @Param("tdn") String tenDangNhap,
            @Param("isbn") String isbn);

    @Procedure(procedureName = "SP_UPDATE_HUY_DH_DMS")
    void updateHuyDonMua(
            @Param("idDonMua") int idDon);
}
