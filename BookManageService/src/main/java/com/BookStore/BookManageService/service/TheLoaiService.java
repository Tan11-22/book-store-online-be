package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.TheLoaiDTO;
import jakarta.validation.constraints.Null;

import java.util.List;

public interface TheLoaiService {
    int laySoLuongSach(int id);
    BookStoreResponse<Boolean> xoaTheLoai(int id);
    BookStoreResponse<Boolean> themTheLoai(String ten);
    BookStoreResponse<Boolean> capNhatTheLoai(int id, String ten);
    BookStoreResponse<List<TheLoaiDTO>> layDSTheLoai();
}
