package com.BookStore.BookManageService.controller;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.SachDTO;
import com.BookStore.BookManageService.model.NhaXuatBan;
import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.model.TheLoai;
import com.BookStore.BookManageService.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-sach-service/sach/")
public class SachController {

    @Autowired
    private SachService sachService;

    @GetMapping("ds")
    public BookStoreResponse<List<SachDTO>> layDSSachQT(@RequestParam("search") String search) {
        return sachService.layDSSachQT(search);
    }
    @GetMapping("ds-tg")
    public BookStoreResponse<List<TacGia>> layTCTG() {
        return sachService.layTCTG();
    }

    @GetMapping("ds-tl")
    public BookStoreResponse<List<TheLoai>> layTCTL() {
        return sachService.layTCTL();
    }
    @GetMapping("ds-nxb")
    public BookStoreResponse<List<NhaXuatBan>> layTCNXB() {
        return sachService.layTCNXB();
    }

    @PostMapping("/them-sach")
    public BookStoreResponse<String> handleFileUpload(
            @RequestParam("isbn") String isbn,
            @RequestParam("tenSach") String tenSach,
            @RequestParam("soTrang") Integer soTrang,
            @RequestParam("khuonKho") String khuonKho,
            @RequestParam("trongLuong") Integer trongLuong,
            @RequestParam("moTa") String moTa,
            @RequestParam("nxb") String nxb,
            @RequestParam("chipTGs") List<String> chipTGs,
            @RequestParam("chipTLs") List<String> chipTLs,
            @RequestParam("files") List<MultipartFile> files
    ) {
        // Xử lý các trường dữ liệu
        System.out.println("ISBN: " + isbn);
        System.out.println("Tên sách: " + tenSach);
        System.out.println("Số trang: " + soTrang);
        System.out.println("Số trang: " + khuonKho);
        System.out.println("Số trang: " + trongLuong);
        System.out.println("Số trang: " + moTa);
        System.out.println("Nhà xuất bản: " + nxb);
        System.out.println("Tác giả: " + chipTGs);
        System.out.println("Thể loại: " + chipTLs);

        // Xử lý các tệp tin
        for (MultipartFile file : files) {
            System.out.println("Received file: " + file.getOriginalFilename());
            // Bạn có thể lưu tệp vào hệ thống hoặc xử lý theo cách khác
        }

        return sachService.themSachMoi(isbn,tenSach,soTrang,khuonKho,trongLuong,moTa,nxb,chipTGs,chipTLs,files);
    }

    @GetMapping("doanh-thu")
    public BookStoreResponse getDoanhThuNam(@RequestParam("year") int year) {
        return sachService.thongKeDoanhThuNam(year);
    }
}
