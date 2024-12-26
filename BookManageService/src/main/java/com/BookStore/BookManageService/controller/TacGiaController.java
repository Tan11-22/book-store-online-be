package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.TacGiaDTO;
import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.service.TacGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-sach-service/tac-gia/")
public class TacGiaController {
    @Autowired
    private TacGiaService tacGiaService;

    @PostMapping("them")
    public BookStoreResponse<Boolean> themTacGia(@RequestBody TacGia tacGia) {
        return tacGiaService.themTacGia(tacGia.getHo(),tacGia.getTen());
    }

    @PostMapping("ds")
    public BookStoreResponse<List<TacGiaDTO>> layDSTacGia() {
        return tacGiaService.layDSTacGia();
    }

    @PostMapping("cap-nhat")
    public BookStoreResponse<Boolean> capNhatTacGia(@RequestBody TacGia tacGia) {
        return tacGiaService.capNhatTacGia(tacGia.getIdTacGia(), tacGia.getHo(),tacGia.getTen());
    }

    @PostMapping("xoa")
    public BookStoreResponse<Boolean> xoaTacGia(@RequestBody TacGia tacGia) {
        return tacGiaService.xoaTacGia(tacGia.getIdTacGia());
    }
}
