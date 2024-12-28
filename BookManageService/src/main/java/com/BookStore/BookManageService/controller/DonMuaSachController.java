package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.service.DonMuaSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/quan-ly-sach-service/don-mua-sach/")
public class DonMuaSachController {
    @Autowired
    private DonMuaSachService donMuaSachService;

    @PostMapping("ds")
    public BookStoreResponse layDSDMS(@RequestBody Map<String, Object> data) {
        int type = (Integer) data.get("type");
        return donMuaSachService.layDanhSachDMS(type);
    }

    @PostMapping("ct-dms")
    public BookStoreResponse layCTDMS(@RequestBody Map<String, Object> data) {
        return donMuaSachService.layDSChiTietDMS(data);
    }

    @PostMapping("cap-nhat-dms")
    public BookStoreResponse capNhatDMS(@RequestBody Map<String, Object> data) {
        return donMuaSachService.capNhatTrangThaiDMS(data);
    }

    @PostMapping("giao-sach-thanh-cong")
    public BookStoreResponse capNhatGiaoDMS(@RequestBody Map<String, Object> data) {
        return donMuaSachService.capNhatTrangThaiGiaoDMS(data);
    }

    @PostMapping("huy-don")
    public BookStoreResponse xoaDon(@RequestBody Map<String, Object> data) {
        return donMuaSachService.huyDonMuaSach(data);
    }
}
