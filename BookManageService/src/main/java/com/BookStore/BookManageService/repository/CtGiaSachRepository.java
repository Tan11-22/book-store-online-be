package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.CTGiaSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface CtGiaSachRepository extends JpaRepository<CTGiaSach, Integer> {
    @Procedure(procedureName = "SP_CAP_NHAT_CTGIA")
    void capNhatGiaSach(
            @Param("idCTGia") int idCTGia,
            @Param("gia") int gia,
            @Param("ngayAD") String ngayAD,
            @Param("ngayKT") String ngayKT
    );
}
