package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.NhaXuatBanDTO;


import java.util.List;

public interface NhaXuatBanService {
    BookStoreResponse<Boolean> xoaNhaXuatBan(String ma);
    BookStoreResponse<Boolean> themNhaXuatBan(String ma, String ten, String chuSoHuu);
    BookStoreResponse<Boolean> capNhatNhaXuatBan(String ma, String ten, String chuSoHuu);
    BookStoreResponse<List<NhaXuatBanDTO>> layDSTacGia();
}
