package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.Gia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface GiaRepository extends JpaRepository<Gia, Integer> {

    @Query(value = "{call SP_GET_SACH_GIA(:search)}",nativeQuery = true)
    List<Map<String, Object>> getSachVaGia(@Param("search") String search);

    @Query(value = "{call SP_GET_LS_GIA_SACH(:isbn)}",nativeQuery = true)
    List<Map<String, Object>> getCTGiaSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_KIEM_TRA_CTGIA(:idGia,:ngayBatDau,:ngayKetThuc, :isbn)}",nativeQuery = true)
    int kiemTraTonTaiGiaSach(
            @Param("idGia") int idGia,
            @Param("ngayBatDau") String ngayBatDau,
            @Param("ngayKetThuc") String ngayKetThuc,
            @Param("isbn") String isbn
    );
}
