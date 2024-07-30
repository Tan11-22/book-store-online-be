package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.CardSach;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.model.Sach;
import com.BookStore.BookService.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sach-service/sach/")

public class SachController {
    @Autowired
    SachService sachService;

    @GetMapping("ds-sach")
    public BookStoreResponse<List<CardSach>> layDSSach(@RequestParam("start") int start,
                                                       @RequestParam("size") int size) {
       return sachService.layDSSach(start, size);
    }


    @GetMapping("chi-tiet-sach")
    public BookStoreResponse<ChiTietSachDTO> layCTSach(@RequestParam("isbn") String isbn) {
        return sachService.layChiTietSach(isbn);
    }


    @GetMapping("tim-sach")
    public BookStoreResponse<List<CardSach>> layDSSach(@RequestParam("search") String search,
                                                       @RequestParam("start") int start,
                                                       @RequestParam("size") int size) {
        return sachService.timSach(search, start, size);
    }
}
