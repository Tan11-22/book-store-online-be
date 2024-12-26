package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.CTGiaDTO;
import com.BookStore.BookManageService.dto.GiaDTO;
import com.BookStore.BookManageService.model.CTGiaSach;
import com.BookStore.BookManageService.service.GiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-sach-service/gia/")
public class GiaController {
    @Autowired
    private GiaService giaService;

    @GetMapping("ds")
    public BookStoreResponse<List<GiaDTO>> getSachVaGia(@RequestParam("search") String search) {
        return giaService.getSachVaGia(search);
    }

    @GetMapping("ct-gia")
    public BookStoreResponse<List<CTGiaDTO>> getCTGia(@RequestParam("isbn") String isbn) {
        return giaService.getCTGiaSach(isbn);
    }

    @PostMapping("them-gia")
    public BookStoreResponse<Boolean> themGia(@RequestBody CTGiaSach data) {
        return giaService.themGiaMoi(data);
    }

    @PostMapping("cap-nhat-gia")
    public BookStoreResponse<Boolean> capNhatGia(@RequestBody CTGiaSach data) {
        System.out.println(data.toString());
        return giaService.capNhatGia(data);
    }
}
