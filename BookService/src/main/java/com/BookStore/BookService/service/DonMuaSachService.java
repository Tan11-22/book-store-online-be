package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.DonMuaSachDTO;
import com.BookStore.BookService.dto.GioHangDTORequest;

import java.util.List;

public interface DonMuaSachService {
    BookStoreResponse<?> datMuaSach(GioHangDTORequest gioHangDTORequest);
    BookStoreResponse<List<DonMuaSachDTO>> layThongTinDatMua(String tenDangNhap);
}
