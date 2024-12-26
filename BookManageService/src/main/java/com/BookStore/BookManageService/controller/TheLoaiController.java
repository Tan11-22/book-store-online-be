package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.TheLoaiDTO;
import com.BookStore.BookManageService.model.TheLoai;
import com.BookStore.BookManageService.service.TheLoaiService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-sach-service/the-loai/")
public class TheLoaiController {
    @Autowired
    private TheLoaiService theLoaiService;

    @PostMapping("them")
    public BookStoreResponse<Boolean> themTheLoai(@RequestBody TheLoai data){
//        System.out.println(tenTheLoai);
        return theLoaiService.themTheLoai(data.getTenTheLoai());
    }

    @PostMapping("xoa")
    public BookStoreResponse<Boolean> xoaTheLoai(@RequestBody TheLoai data){
        return theLoaiService.xoaTheLoai(data.getIdTheLoai());
    }

    @PostMapping("cap-nhat")
    public BookStoreResponse<Boolean> capNhatTheLoai(@RequestBody TheLoai data){
        return theLoaiService.capNhatTheLoai(data.getIdTheLoai(), data.getTenTheLoai());
    }

    @PostMapping("ds")
    public BookStoreResponse<List<TheLoaiDTO>> dsTheLoai(){
        return theLoaiService.layDSTheLoai();
    }
}
