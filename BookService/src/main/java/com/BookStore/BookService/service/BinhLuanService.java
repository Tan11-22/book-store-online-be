package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface BinhLuanService {
    BookStoreResponse binhLuan(Map<String, Object> data);
    BookStoreResponse danhSachBinhLuanCuaSach(Map<String, Object> data);
    BookStoreResponse soLuongBinhLuan(String isbn);
}
