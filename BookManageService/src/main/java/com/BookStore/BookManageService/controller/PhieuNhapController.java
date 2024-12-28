package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.DonNhapSachRequestDTO;
import com.BookStore.BookManageService.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quan-ly-sach-service/phieu-nhap/")
public class PhieuNhapController {
    @Autowired
    private PhieuNhapService phieuNhapService;

    @PostMapping("tao-phieu")
    public BookStoreResponse<Boolean> taoPhieuNhap(@RequestBody DonNhapSachRequestDTO data) {
        return phieuNhapService.taoPhieuNhap(data);
    }

    @GetMapping("ds")
    public BookStoreResponse getAllPN() {
        return phieuNhapService.getAllPhieuNhap();
    }

    @GetMapping("chi-tiet-phieu-nhap")
    public BookStoreResponse getCtPhieuNhap(@RequestParam("spn") int soPhieuNhap) {
        return phieuNhapService.getCTPN(soPhieuNhap);
    }

    @GetMapping("ctpn-theo-don")
    public BookStoreResponse getCtPhieuNhapTheoDon(@RequestParam("idDon") int idDon) {
        return phieuNhapService.getCTPNTheoDonNhap(idDon);
    }
}
