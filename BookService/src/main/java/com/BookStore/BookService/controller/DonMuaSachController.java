package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.DonMuaSachDTO;
import com.BookStore.BookService.service.DonMuaSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sach-service/don-mua-sach/")
public class DonMuaSachController {

    @Autowired
    private DonMuaSachService donMuaSachService;

    @GetMapping("ds")
    public BookStoreResponse<List<DonMuaSachDTO>> getDSDMS(@RequestParam("tdn") String tenDangNhap){
        return donMuaSachService.layThongTinDatMua(tenDangNhap);
    }
}
