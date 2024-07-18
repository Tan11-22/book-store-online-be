package com.BookStore.BookService.repository;

import com.BookStore.BookService.dto.BinhLuanDTO;
import com.BookStore.BookService.dto.SachDTO;
import com.BookStore.BookService.model.HinhAnh;
import com.BookStore.BookService.model.Sach;

import com.BookStore.BookService.model.TacGia;
import com.BookStore.BookService.model.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SachRepository extends JpaRepository<Sach, String> {
    @Query(value = "{call SP_LAY_DS_SACH()}", nativeQuery = true)
    List<Sach> layDSSach();

    @Query(value = "{call SP_LAY_CHI_TIET_SACH(:isbn)}", nativeQuery = true)
    Map<String, Object> layChiTietSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_HINH_ANH_SACH(:isbn)}", nativeQuery = true)
    List<HinhAnh> layDanhSachHinhAnhSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_TAC_GIA_SACH(:isbn)}", nativeQuery = true)
    List<TacGia> layDanhSachTacGiaSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_THE_LOAI_SACH(:isbn)}", nativeQuery = true)
    List<TheLoai> layDanhSachTheLoaiSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_BINH_LUAN_CUA_SACH(:isbn)}", nativeQuery = true)
    List<Map<String, Object>> layDanhSachBinhLuanCuaSach(@Param("isbn") String isbn);

}
