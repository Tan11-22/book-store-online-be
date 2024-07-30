package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.NhaXuatBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface NhaXuatBanRepository extends JpaRepository<NhaXuatBan, String> {
    @Procedure(procedureName = "SP_THEM_NHA_XUAT_BAN")
    void themNhaXuatBan(
            @Param("manxb") String maNXB,
            @Param("tennxb") String tenNXB,
            @Param("chusohuu") String chuSoHuu
    );
    @Procedure(procedureName = "SP_CAP_NHAT_NHA_XUAT_BAN")
    void capNhatNhaXuatBan(
            @Param("manxb") String maNXB,
            @Param("tennxb") String tenNXB,
            @Param("chusohuu") String chuSoHuu
    );

    @Procedure(procedureName = "SP_XOA_NHA_XUAT_BAN")
    void xoaNhaXuatBan(
            @Param("manxb") String maNXB
    );

    @Query(value = "{call SP_LAY_SL_SACH_CUA_NXB(:manxb)}",nativeQuery = true)
    int laySLSachCuaNXB(@Param("manxb") String maNXB);
}
