package com.BookStore.BookService.repository;

import com.BookStore.BookService.model.BinhLuanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BinhLuanRepository extends JpaRepository<BinhLuanEntity, Integer> {

        @Query(value = "{call SP_LAY_DS_BINH_LUAN_CUA_SACH(:isbn, :start, :size)}", nativeQuery = true)
        List<Map<String, Object>> layDanhSachBinhLuanCuaSach(@Param("isbn") String isbn,
                                                             @Param("start") int start,
                                                             @Param("size") int size);

        @Query(value = "{call SP_COUNT_SL_BINH_LUAN_CUA_SACH(:isbn)}", nativeQuery = true)
        int demSoLuongBinhLuan(@Param("isbn") String isbn);

}
