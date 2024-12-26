package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.DonNhapSach;
import com.BookStore.BookManageService.model.NhaXuatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DonNhapSachRepository extends JpaRepository<DonNhapSach, Integer> {
    @Query(value = "{call SP_LAY_DS_SACH_THEO_NXB(:manxb)}", nativeQuery = true)
    List<Map<String, Object>> layDSSachtheoNXB(@Param("manxb") String manxb);

    @Query(value = "{call sp_GetAllDonNhapSach()}", nativeQuery = true)
    List<Map<String, Object>> layAllDonNhapSach();

    @Query(value = "{call SP_GET_ALL_DNS_TK(:keyword)}", nativeQuery = true)
    List<Map<String, Object>> layAllDNSTK(@Param("keyword") String keyword);

    @Query(value = "{call SP_GET_DONNHAPSACH_ID(:id)}", nativeQuery = true)
    Map<String, Object> getDonNhapSachTheoId(@Param("id") int id);



    @Query(value = "{call SP_GET_CTDONNHAPSACH_IDDON(:id)}", nativeQuery = true)
    List<Map<String, Object>> getCTDonNhapSachTheoIdDon(@Param("id") int id);
    @Query(value = "{call SP_LAY_CTDONNHAPSACH_IDDON(:id)}", nativeQuery = true)
    List<Map<String, Object>> layCTDonNhapSachTheoIdDon(@Param("id") int id);
//


//    @Query(value = "{call SP_LAY_ALL_NXB()}", nativeQuery = true)
//    List<NhaXuatBan> layAllNXB();

    @Query(value = "SELECT MAX(IDDONNHAPSACH) FROM DONNHAPSACH WHERE MANHAXUATBAN =:maNXB AND NGAYDAT =:ngayDat", nativeQuery = true)
    int layIDDonNhapSach(@Param("maNXB") String maNXB,
                         @Param("ngayDat") String ngayDat);

    @Query(value = "{call SP_KIEM_TRA_XOA_DON_NHAP(:idDonNhap)}",nativeQuery = true)
    int laySLPhieuNhapCuaDonNhap(@Param("idDonNhap") int id);

    @Procedure(procedureName = "SP_XOA_CHI_TIET_DON_NHAP")
    void xoaCTDonNhapSach(@Param("idDonNhap") int id);

    @Procedure(procedureName = "SP_XOA_DON_NHAP_SACH")
    void xoaDonNhapSach(@Param("idDonNhap") int id);
}
