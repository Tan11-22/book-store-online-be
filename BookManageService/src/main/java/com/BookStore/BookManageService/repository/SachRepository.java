package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SachRepository extends JpaRepository<Sach, String> {
    @Procedure(procedureName = "SP_THEM_SACH")
    void themSach(@Param("isbn") String isbn,
                  @Param("tensach") String tenSach,
                  @Param("khuonkho") String khuonKho, // có thể gán == null
                  @Param("sotrang") Integer soTrang,
                  @Param("trongluong") Integer trongLuong,
                  @Param("mota") String moTa, // allow null
                  @Param("manxb") String maNXB);

    @Procedure(procedureName = "SP_THEM_SANG_TAC")
    void themSangTac(@Param("isbn") String isbn,
                  @Param("idTacGia") Integer idTacGia);

    @Procedure(procedureName = "SP_THEM_THE_LOAI_SACH")
    void themTheLoaiSach(@Param("isbn") String isbn,
                  @Param("idTheLoai") Integer idTheLoai);

    @Procedure(procedureName = "SP_THEM_HINH_ANH")
    void themHinhAnh(@Param("isbn") String isbn,
                         @Param("file") String file);

    @Query(value = "{call SP_LAY_DS_SACH_QT(:search)}", nativeQuery = true)
    List<Map<String, Object>> layDSSachQT(@Param("search") String search);

    @Query(value = "{call SP_LAY_DS_TG_QT(:isbn)}", nativeQuery = true)
    List<Map<String, Object>> layDSTGQT(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_TL_QT(:isbn)}", nativeQuery = true)
    List<Map<String, Object>> layDSTLQT(@Param("isbn") String isbn);


    @Query(value = "{call SP_LAY_DS_HA_QT(:isbn)}", nativeQuery = true)
    List<Map<String, Object>> layDSHAQT(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_TC_TG()}", nativeQuery = true)
    List<Map<String, Object>> layTCTG();

    @Query(value = "{call SP_LAY_TC_TL()}", nativeQuery = true)
    List<Map<String, Object>> layTCTL();

    @Query(value = "{call SP_LAY_TC_NXB()}", nativeQuery = true)
    List<Map<String, Object>> layTCNXB();

    @Query(value = "{call sp_Check_ISBN(:isbn)}", nativeQuery = true)
    Integer checkISBN(@Param("isbn") String isbn);
}
