package com.BookStore.AuthenticationService.repository;

import com.BookStore.AuthenticationService.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Map;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    @Query(value = "{call SP_LOGIN(:tendangnhap)}", nativeQuery = true)
    Map<String, Object> loadUser(@Param("tendangnhap") String tenDangNhap);

    @Query(value = "{call SP_CHECK_TDN(:tdn)}", nativeQuery = true)
    Integer checkTDN(@Param("tdn") String tenDangNhap);

    @Query(value = "{call SP_CHECK_EMAIL(:email)}", nativeQuery = true)
    Integer checkEmail(@Param("email") String email);

    @Procedure(procedureName = "SP_TAO_TAI_KHOAN")
    void taoTaiKhoan(@Param("tdn") String tdn,
                     @Param("mk") String mk,
                     @Param("idQuyen") Integer idQuyen);
    @Procedure(procedureName = "SP_TAO_KHACH_HANG")
    void taoKhachHang(@Param("tdn") String tdn,
                     @Param("email") String email,
                     @Param("ho") String ho,
                     @Param("ten") String ten,
                     @Param("gt") Boolean gioiTinh,
                     @Param("diaChi") String diaChi,
                     @Param("ngaySinh") Date ngaySinh,
                     @Param("sdt") String sdt);

    @Query(value = "{call SP_LAY_QUYEN_CUA_USER(:tdn)}", nativeQuery = true)
    String layQuyenCuaTK(@Param("tdn") String tenDangNhap);

    @Procedure(procedureName = "SP_DOI_MAT_KHAU")
    void doiMatKhau(@Param("tdn") String tdn,
                     @Param("newMK") String newMK);

    @Query(value = "SELECT EMAIL FROM KHACHHANG WHERE TENDANGNHAP=:tdn", nativeQuery = true)
    String getEmailKH(@Param("tdn") String tenDangNhap);

    @Query(value = "SELECT EMAIL FROM NHANVIEN WHERE MANHANVIEN=:tdn", nativeQuery = true)
    String getEmailNV(@Param("tdn") String tenDangNhap);
}
