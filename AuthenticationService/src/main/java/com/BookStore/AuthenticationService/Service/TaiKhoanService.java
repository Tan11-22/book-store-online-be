package com.BookStore.AuthenticationService.Service;

import com.BookStore.AuthenticationService.dto.BookStoreResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public interface TaiKhoanService {
    BookStoreResponse<Boolean> dangKyTaiKhoan(String tenDangNhap,
                                              String matKhau,
                                              String email,
                                              String ho,
                                              String ten,
                                              String gioiTinh,
                                              String diaChi,
                                              Date ngaySinh,
                                              String soDienThoai);
    String lauQuyenCuaUser(String tenDangNhap);
    BookStoreResponse<Boolean> thayDoiMatKhau(String username, String password, String newpassword);
    BookStoreResponse quenMatKhau(String email, String username);
}
