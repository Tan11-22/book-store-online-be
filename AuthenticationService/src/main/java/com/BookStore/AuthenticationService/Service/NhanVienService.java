package com.BookStore.AuthenticationService.Service;

import com.BookStore.AuthenticationService.dto.BookStoreResponse;

public interface NhanVienService {
    BookStoreResponse getInfoNhanVien(String tenDangNhap);
}
