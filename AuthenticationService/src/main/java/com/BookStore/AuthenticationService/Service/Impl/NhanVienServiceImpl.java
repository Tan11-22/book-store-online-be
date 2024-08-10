package com.BookStore.AuthenticationService.Service.Impl;

import com.BookStore.AuthenticationService.Service.NhanVienService;
import com.BookStore.AuthenticationService.dto.BookStoreResponse;
import com.BookStore.AuthenticationService.model.KhachHang;
import com.BookStore.AuthenticationService.model.NhanVien;
import com.BookStore.AuthenticationService.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Override
    public BookStoreResponse getInfoNhanVien(String tenDangNhap) {
        NhanVien result = nhanVienRepository.findById(tenDangNhap).get();
        return BookStoreResponse.<NhanVien>builder()
                .code(200)
                .status("Lấy thông tin NhanVien thành công!")
                .data(result).build();
    }
}
