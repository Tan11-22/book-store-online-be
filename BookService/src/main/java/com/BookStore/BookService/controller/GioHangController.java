package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.GioHangDTO;
import com.BookStore.BookService.model.GioHang;
import com.BookStore.BookService.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

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


    @PostMapping("dat-mua-sach")
    public String datMuaSach(
//            @RequestParam("diaChi") String diaChi,
//                             @RequestParam("ptth") String tenSach,
                             @RequestParam("sachs") List<GioHangDTO> sachs) {
        for(GioHangDTO x : sachs) {
            System.out.println(x.toString());
        }

        return "Check api";
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
    public BookStoreResponse<Boolean> xoaSachTrongGioHang(@RequestBody Map<String, String> data) {
        return gioHangService.xoaSachTrongGioHang(Integer.parseInt(data.get("idGioHang")));
    }

    @GetMapping("so-luong")
    public BookStoreResponse<Integer> laySlSachTrongGH(@RequestParam("tendangnhap") String ten) {
        return gioHangService.laySLSachTrongGH(ten);
    }


}
