package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.service.BinhLuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sach-service/binh-luan/")
public class BinhLuanController {

    @Autowired
    private BinhLuanService binhLuanService;

    @PostMapping("/tao")
    public BookStoreResponse taoBinhLuan(@RequestBody Map<String, Object> data) {
        return binhLuanService.binhLuan(data);
    }

    @PostMapping("/ds-binh-luan")
    public BookStoreResponse layDSBinhLuan(@RequestBody Map<String, Object> data) {
        return binhLuanService.danhSachBinhLuanCuaSach(data);
    }

    @PostMapping("/so-luong-binh-luan")
    public BookStoreResponse laySoLuongBinhLuan(@RequestBody Map<String, Object> data) {
        return binhLuanService.soLuongBinhLuan((String) data.get("isbn"));
    }
}
