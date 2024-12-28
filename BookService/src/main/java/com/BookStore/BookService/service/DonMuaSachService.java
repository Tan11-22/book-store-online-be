package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.DonMuaSachDTO;
import com.BookStore.BookService.dto.GioHangDTORequest;

import java.util.List;
import java.util.Map;

public interface DonMuaSachService {
    BookStoreResponse<?> datMuaSach(GioHangDTORequest gioHangDTORequest);
    BookStoreResponse<List<DonMuaSachDTO>> layThongTinDatMua(String tenDangNhap);
    BookStoreResponse updateTrangThaiDonMuaSauThanhToan(String tenDangNhap, int trangThai);
    BookStoreResponse huyDonMuaSach(Map<String, Object> data);
}
