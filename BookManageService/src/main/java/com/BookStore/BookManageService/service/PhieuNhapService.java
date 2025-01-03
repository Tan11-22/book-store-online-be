package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.DonNhapSachRequestDTO;

public interface PhieuNhapService {
    BookStoreResponse<Boolean> taoPhieuNhap(DonNhapSachRequestDTO donNhapSachRequestDTO);
    BookStoreResponse getAllPhieuNhap();
    BookStoreResponse getCTPN(int soPhieuNhap);
    BookStoreResponse getCTPNTheoDonNhap(int idDon);
}
