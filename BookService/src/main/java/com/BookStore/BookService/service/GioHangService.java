package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.GioHangDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface GioHangService {
    BookStoreResponse<Map<String,Object>> layChiTietGioHang(String tenDangNhap);
    BookStoreResponse<Boolean> themSachVaoGioHang(String ten, String isbn, int soLuong);
    BookStoreResponse<Boolean> capNhatSachTrongGioHang(int id, int soLuong);
    BookStoreResponse<Boolean> xoaSachTrongGioHang(int id);


    BookStoreResponse<Integer> laySLSachTrongGH(String tenDangNhap);
}
