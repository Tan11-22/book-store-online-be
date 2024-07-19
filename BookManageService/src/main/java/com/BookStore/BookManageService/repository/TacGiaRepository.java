package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.TacGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TacGiaRepository extends JpaRepository<TacGia, Integer> {
    @Query(value = "{call SP_LAY_SO_LUONG_SANG_TAC(:id)}", nativeQuery = true)
    int laySoLuongSangTac(@Param("id") int id);

    @Procedure(procedureName = "SP_XOA_TAC_GIA")
    void xoaTacGia(@Param("id") int id);

    @Procedure(procedureName = "SP_THEM_TAC_GIA")
    void themTacGia(@Param("ho") String ho, @Param("ten") String ten);

    @Procedure(procedureName = "SP_CAP_NHAT_TAC_GIA")
    void capNhatTacGia(@Param("id") int id, @Param("ho") String ho, @Param("ten") String ten);

    @Query(value = "{call SP_LAY_DS_TAC_GIA()}", nativeQuery = true)
    List<Map<String, Object>> layDSTacGia();
}
