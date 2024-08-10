package com.BookStore.BookService.controller;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.DonMuaSachDTO;
import com.BookStore.BookService.service.DonMuaSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sach-service/don-mua-sach/")
public class DonMuaSachController {

    @Autowired
    private DonMuaSachService donMuaSachService;

    @GetMapping("ds")
    public BookStoreResponse<List<DonMuaSachDTO>> getDSDMS(@RequestParam("tdn") String tenDangNhap){
        return donMuaSachService.layThongTinDatMua(tenDangNhap);
    }


    @PostMapping("cap-nhat-don-mua")
    public  BookStoreResponse capNhatDonMua(@RequestBody Map<String, Object> data) {
        System.out.println(data);
        return donMuaSachService.updateTrangThaiDonMuaSauThanhToan((String) data.get("tdn"), (Integer) data.get("trangThai"));
    }
}
