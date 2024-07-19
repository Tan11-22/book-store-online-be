package com.BookStore.BookManageService.repository;

import com.BookStore.BookManageService.model.TheLoaiSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface TheLoaiSachRepository extends JpaRepository<TheLoaiSach, Integer> {
    @Procedure(procedureName = "SP_THEM_THE_LOAI_SACH")
    void themTheLoaiSach(@Param("idtl") Integer id,
                         @Param("isbn") String isbn);
}
