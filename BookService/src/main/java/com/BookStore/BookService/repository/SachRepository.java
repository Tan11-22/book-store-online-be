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
    // query thông tin sách dạng card dùng cho trang chủ , thể loại , tìm kiếm
    @Query(value = "{call SP_LAY_DS_SACH(:start, :size)}", nativeQuery = true)
    List<Map<String, Object>> layDSSach(@Param("start") int start, @Param("size") int size);


    @Query(value = "{call SP_TIM_SACH(:search, :start, :size)}", nativeQuery = true)
    List<Map<String, Object>> timSach(@Param("search") String search, @Param("start") int start, @Param("size") int size);


    // những câu query để lấy thông tin chi tiết 1 cuốn sách
    @Query(value = "{call SP_LAY_CHI_TIET_SACH(:isbn)}", nativeQuery = true)
    Map<String, Object> layChiTietSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_HINH_ANH_SACH(:isbn)}", nativeQuery = true)
    List<Map<String,Object>> layDanhSachHinhAnhSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_TAC_GIA_SACH(:isbn)}", nativeQuery = true)
    List<Map<String,Object>> layDanhSachTacGiaSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_THE_LOAI_SACH(:isbn)}", nativeQuery = true)
    List<Map<String,Object>> layDanhSachTheLoaiSach(@Param("isbn") String isbn);

    @Query(value = "{call SP_LAY_DS_BINH_LUAN_CUA_SACH(:isbn)}", nativeQuery = true)
    List<Map<String, Object>> layDanhSachBinhLuanCuaSach(@Param("isbn") String isbn);

}
