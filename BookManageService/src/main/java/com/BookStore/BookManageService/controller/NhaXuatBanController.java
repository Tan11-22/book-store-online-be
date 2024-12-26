package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.NhaXuatBanDTO;
import com.BookStore.BookManageService.dto.TacGiaDTO;
import com.BookStore.BookManageService.model.NhaXuatBan;
import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.service.NhaXuatBanService;
import com.BookStore.BookManageService.service.TacGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-sach-service/nxb/")
public class NhaXuatBanController {
    @Autowired
    private NhaXuatBanService nhaXuatBanService;

    @PostMapping("them")
    public BookStoreResponse<Boolean> themnxb(@RequestBody NhaXuatBan nxb) {
        return nhaXuatBanService.themNhaXuatBan(nxb.getMaNhaXuatBan(),nxb.getTenNhaXuatBan(),nxb.getChuSoHuu());
    }

    @PostMapping("ds")
    public BookStoreResponse<List<NhaXuatBanDTO>> layDSNXB() {
        return nhaXuatBanService.layDSTacGia();
    }

    @PostMapping("cap-nhat")
    public BookStoreResponse<Boolean> capNhatNXB(@RequestBody NhaXuatBan nxb) {
        return nhaXuatBanService.capNhatNhaXuatBan(nxb.getMaNhaXuatBan(),nxb.getTenNhaXuatBan(),nxb.getChuSoHuu());
    }

    @PostMapping("xoa")
    public BookStoreResponse<Boolean> xoaNXB(@RequestBody NhaXuatBan nxb) {
        return nhaXuatBanService.xoaNhaXuatBan(nxb.getMaNhaXuatBan());
    }
}
