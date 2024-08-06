package com.BookStore.AuthenticationService.Service;

import com.BookStore.AuthenticationService.dto.BookStoreResponse;
import com.BookStore.AuthenticationService.model.KhachHang;

public interface KhachHangService {
    BookStoreResponse<KhachHang> getInfoKhachHang(String tenDangNhap);
}
