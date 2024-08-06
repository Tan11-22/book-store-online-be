package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.*;
import com.BookStore.BookManageService.model.NhaXuatBan;
import com.BookStore.BookManageService.service.DonNhapSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-sach-service/don-nhap-sach/")
public class DonNhapSachController {
    @Autowired
    private DonNhapSachService donNhapSachService;

    @GetMapping("ds")
    public BookStoreResponse<List<DonNhapSachDTO>> layAllDNS() {
        return  donNhapSachService.layALLDonNhapSach();
    }

    @GetMapping("ds-nxb")
    public BookStoreResponse<List<NhaXuatBan>> layAllNXB() {
        return donNhapSachService.layALLNXB();
    }

    @GetMapping("ds-sach-nxb")
    public BookStoreResponse<List<SachDNDTO>> laySachTheoNXB(@RequestParam("maNXB") String maNXB) {
        return donNhapSachService.laySachNhapTheoNXB(maNXB);
    }

    @PostMapping("dat-nhap-sach")
    public BookStoreResponse<Boolean> datSach(@RequestBody DonNhapSachRequestDTO data) {
        System.out.println("check"+ data.getDonNhapSach().toString());
        return donNhapSachService.datNhapSach(data);
    }

    @GetMapping("huy-don-nhap-sach")
    public  BookStoreResponse<Boolean> huyDatSach(@RequestParam("idDonNhap") int idDonNhap) {
        return donNhapSachService.huyDonNhapSach(idDonNhap);
    }

    @GetMapping("chi-tiet-don-nhap-sach")
    public BookStoreResponse<DonNhapSachResponseDTO> getChiTietDon(@RequestParam("idDonNhap") int idDonNhap) {
        return donNhapSachService.getChiTietDonTheoID(idDonNhap);
    }

}
