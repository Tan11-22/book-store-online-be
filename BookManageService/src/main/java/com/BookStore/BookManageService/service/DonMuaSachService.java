package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;

import java.util.Map;

public interface DonMuaSachService {
    BookStoreResponse layDanhSachDMS(int type);
    BookStoreResponse capNhatTrangThaiDMS(Map<String, Object> data);
    BookStoreResponse layDSChiTietDMS(Map<String, Object> data);
    BookStoreResponse capNhatTrangThaiGiaoDMS(Map<String, Object> data);
    BookStoreResponse huyDonMuaSach(Map<String, Object> data);
}
