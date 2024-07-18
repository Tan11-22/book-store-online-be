package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.model.Sach;
import com.BookStore.BookService.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sach-service/")
public class SachController {
    @Autowired
    SachService sachService;

    @GetMapping("/ds-sach")
    public ResponseEntity<List<Sach>> layDSSach() {
        List<Sach> sachs = sachService.layDSSach();
        return ResponseEntity.ok(sachs);
    }

    @GetMapping("/chi-tiet-sach")
    public BookStoreResponse<ChiTietSachDTO> layCTSach(@RequestParam("isbn") String isbn) {
        return sachService.layChiTietSach(isbn);
    }
}
