package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.SachBanChayDTO;
import com.BookStore.BookManageService.dto.SachDTO;
import com.BookStore.BookManageService.model.NhaXuatBan;
import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.model.TheLoai;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SachService {
    BookStoreResponse<List<SachDTO>> layDSSachQT(String search);
    BookStoreResponse<String> themSachMoi(String isbn,
                                          String tenSach,
                                          Integer soTrang,
                                          String khuonKho,
                                          Integer trongLuong,
                                          String moTa,
                                          String nxb,
                                          List<String> chipTGs,
                                          List<String> chipTLs,
                                          List<MultipartFile> files);

    BookStoreResponse<String> capNhatSach(String isbn,
                                          String tenSach,
                                          Integer soTrang,
                                          String khuonKho,
                                          Integer trongLuong,
                                          String moTa,
                                          String nxb,
                                          List<String> tacGias,
                                          List<String> theLoais,
                                          List<String> anhXoa,
                                          List<MultipartFile> anhMoi);
    BookStoreResponse xoaSach(String isbn);
    BookStoreResponse<List<TacGia>> layTCTG();
    BookStoreResponse<List<TheLoai>> layTCTL();
    BookStoreResponse<List<NhaXuatBan>> layTCNXB();
    BookStoreResponse thongKeDoanhThuNam(int year);
    BookStoreResponse<List<SachBanChayDTO>> layTop10SachBanChay(int thang, int nam);


}
