package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.PhieuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PhieuNhapRepository extends JpaRepository<PhieuNhap,Integer> {
    @Procedure(procedureName = "SP_TAO_PHIEU_NHAP")
    void taophieuNhap(@Param("maNV") String maNV,
                      @Param("idDon") int idDon);

    @Procedure(procedureName = "SP_INSERT_CT_PN")
    void insertCTPN(@Param("soPN") int soPN,
                      @Param("isbn") String isbn,
                    @Param("sl") int soLuong,
                    @Param("dg") int donGia);

    @Procedure(procedureName = "SP_UPDATE_SL_SACH")
    void updateSoLuongSach(
                    @Param("isbn") String isbn,
                    @Param("soLuong") int soLuong);

    @Query(value = "{call SP_GET_SO_PN_MANV_IDDON(:maNV, :idDon)}",nativeQuery = true)
    int getSoPN(@Param("maNV") String maNV,
                @Param("idDon") int idDon);

    @Query(value = "SELECT SOLUONG FROM SACH WHERE ISBN=:isbn",nativeQuery = true)
    int getSoLuongSach(@Param("isbn") String isbn);
//
//    @Query(value = "{call SP_GET_ALL_PHIEU_NHAP()}",nativeQuery = true)
//    List<Map<String,Object>> getAllPN();
}
