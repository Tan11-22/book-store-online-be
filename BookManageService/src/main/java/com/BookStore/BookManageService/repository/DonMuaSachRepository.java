package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.DonMuaSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DonMuaSachRepository extends JpaRepository<DonMuaSach, Integer> {
    @Query(value = "{call SP_LAY_DS_DMS_QT(:tt)}", nativeQuery = true)
    List<Map<String, Object>> layDSDonMuaSachQT(@Param("tt") int trangThai);

//    @Query(value = "{call SP_LAY_DS_DMS_CAN_GIAO()}", nativeQuery = true)
//    List<Map<String, Object>> layDSDonMuaSachCanGiao();

    @Procedure(procedureName = "SP_UPDATE_TRANG_THAI_DH_DMS")
    void updateTrangThaiDon_DMS(
            @Param("idDon") int idDon,
            @Param("manv") String manv);

    @Procedure(procedureName = "SP_CAP_NHAT_TT_DMS_CHO_GIAO")
    void updateTrangThaiGiaoHang_DMS(
            @Param("idDonMua") int idDon,
            @Param("manv") String manv
           );

    @Query(value = "{call SP_LAY_CT_DMS(:idDon)}", nativeQuery = true)
    List<Map<String, Object>> layCTDMS( @Param("idDon") int idDon);

    @Procedure(procedureName = "SP_UPDATE_HUY_DH_DMS")
    void updateHuyDonMua(
            @Param("idDonMua") int idDon);

    @Query(value = "{call SP_GET_ISBN_BY_ID_DM(:idDonMua)}",nativeQuery = true)
    List<Map<String, Object>> getISBNbyIdDon(@Param("idDonMua") int idDonMua);

    @Query(value = "SELECT SOLUONG FROM SACH WHERE ISBN=:isbn",nativeQuery = true)
    int getSoLuongSach(@Param("isbn") String isbn);

    @Procedure(procedureName = "SP_UPDATE_SL_SACH")
    void updateSoLuongSach(
            @Param("isbn") String isbn,
            @Param("soLuong") int soLuong);
}
