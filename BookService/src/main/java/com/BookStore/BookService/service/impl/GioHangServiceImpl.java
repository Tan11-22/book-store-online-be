package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.GioHangDTO;
import com.BookStore.BookService.repository.GioHangRepository;
import com.BookStore.BookService.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public BookStoreResponse<List<GioHangDTO>> layChiTietGioHang(String tenDangNhap) {
        return null;
    }

    @Override
    public BookStoreResponse<Boolean> themSachVaoGioHang(String ten, String isbn, int soLuong) {
        return null;
    }

    @Override
    public BookStoreResponse<Boolean> capNhatSachTrongGioHang(int id, int soLuong) {
        return null;
    }

    @Override
    public BookStoreResponse<Boolean> xoaSachTrongGioHang(int id) {
        return null;
    }

    @Override
    public Integer layIdGioHang(String ten, String isbn) {
        return gioHangRepository.layIdGioHang(ten, isbn);
    }
}
