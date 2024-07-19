package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.service.TheLoaiService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quan-ly-sach-service/the-loai/")
public class TheLoaiController {
    @Autowired
    private TheLoaiService theLoaiService;

    @PostMapping("them")
    public BookStoreResponse<Boolean> themTheLoai(@RequestBody  String tenTheLoai){
        System.out.println(tenTheLoai);
        return theLoaiService.themTheLoai(tenTheLoai);
    }
}
