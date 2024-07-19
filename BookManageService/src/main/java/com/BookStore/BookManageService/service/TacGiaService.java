package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.TacGiaDTO;

import java.util.List;

public interface TacGiaService {
    int laySoLuongSangTac(int id);
    BookStoreResponse<Boolean> xoaTacGia(int id);
    BookStoreResponse<Boolean> themTacGia(String ho, String ten);
    BookStoreResponse<Boolean> capNhatTacGia(int id, String ho, String ten);
    BookStoreResponse<List<TacGiaDTO>> layDSTacGia();
}
