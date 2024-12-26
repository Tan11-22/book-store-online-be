package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {
    @Query(value = "{call SP_LAY_SO_LUONG_SACH_CUA_THE_LOAI(:id)}", nativeQuery = true)
    int laySoLuongSach(@Param("id") int id);

    @Query(value = "{call  SP_LAY_DS_THE_LOAI()}", nativeQuery = true)
    List<Map<String, Object>> layDSTheLoai();

    @Procedure(procedureName = "SP_XOA_THE_LOAI")
    void xoaTheLoai(@Param("id") int id);

    @Procedure(procedureName = "SP_THEM_THE_LOAI")
    void themTheLoai(@Param("ten") String tenTheLoai);

    @Procedure(procedureName = "SP_CAP_NHAT_THE_LOAI")
    void capNhatTheLoai(@Param("id") int id, @Param("ten") String tenTheLoai);
}
