package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.GioHangDTO;
import com.BookStore.BookService.model.GioHang;
import com.BookStore.BookService.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/sach-service/gio-hang/")
public class GioHangController {

    @Autowired
    private GioHangService gioHangService;
    // Xem chi tiết
    @GetMapping("chi-tiet-gio-hang")
    public BookStoreResponse<List<GioHangDTO>> xemChiTietGioHang(@RequestParam("tendangnhap") String ten) {
        return gioHangService.layChiTietGioHang(ten);
    }

    // Thêm sách
    /**
      Requestbody
        tenDangNhap
        isbn
        soLuong
     */
    @PostMapping("/them")
    public BookStoreResponse<Boolean> themSachVaoGiohang(@RequestBody GioHang data) {
        return gioHangService.themSachVaoGioHang(data.getTenDangNhap(), data.getIsbn(), data.getSoLuong());
    }
    // Cập nhật sách
    @PostMapping("/cap-nhat")
    public BookStoreResponse<Boolean> capNhatSachTrongGioHang(@RequestBody GioHang data) {
        return gioHangService.capNhatSachTrongGioHang(data.getIdGioHang(), data.getSoLuong());
    }
    // Xoá sách

    @PostMapping("/xoa")
    public BookStoreResponse<Boolean> xoaSachTrongGioHang(@RequestBody GioHang data) {
        return gioHangService.xoaSachTrongGioHang(data.getIdGioHang());
    }
}
