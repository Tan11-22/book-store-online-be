package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.CardSach;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.dto.TacGiaDTO;
import com.BookStore.BookService.model.Sach;
import com.BookStore.BookService.model.TacGia;
import com.BookStore.BookService.model.TheLoai;
import com.BookStore.BookService.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("ds-sach-ban-chay")
    public BookStoreResponse<List<CardSach>> layDSSachBanChay(@RequestParam("start") int start,
                                                       @RequestParam("size") int size) {
        return sachService.layDSSachBanChay(start, size);
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

    @GetMapping("tong-sl-sach-tim")
    public BookStoreResponse<Integer> layTongSLSachTim(@RequestParam("search") String search) {
        return sachService.demSLSachTimRa(search);
    }

    @PostMapping("search")
    public BookStoreResponse<List<CardSach>> search(
            @RequestBody Map<String, Object> req
    ) {
        return sachService.timSach1(req);
    }

    @PostMapping("search-amount")
    public BookStoreResponse<Integer> searchAmount(
            @RequestBody Map<String, Object> req
    ) {
        return sachService.countTimSach1(req);
    }

    @GetMapping("ds-tg")
    public BookStoreResponse<List<TacGiaDTO>> layThongTinTacGia() {
        return sachService.layTCTG();
    }

    @GetMapping("ds-tl")
    public BookStoreResponse<List<TheLoai>> layThongTinTheLoai() {
        return sachService.layTCTL();
    }

    @GetMapping("sach-tuong-tu")
    public BookStoreResponse<List<CardSach>> getSachCungTheLoai(
            @RequestParam("isbn") String isbn
    ) {
        return sachService.getSachCungTheLoai(isbn);
    }
}
