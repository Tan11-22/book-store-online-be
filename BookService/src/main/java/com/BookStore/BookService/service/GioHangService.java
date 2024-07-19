package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.GioHangDTO;

import java.util.List;

public interface GioHangService {
    BookStoreResponse<List<GioHangDTO>> layChiTietGioHang(String tenDangNhap);
    BookStoreResponse<Boolean> themSachVaoGioHang(String ten, String isbn, int soLuong);
    BookStoreResponse<Boolean> capNhatSachTrongGioHang(int id, int soLuong);
    BookStoreResponse<Boolean> xoaSachTrongGioHang(int id);
    Integer layIdGioHang(String ten, String isbn);
}
