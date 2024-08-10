package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
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
    BookStoreResponse<List<TacGia>> layTCTG();
    BookStoreResponse<List<TheLoai>> layTCTL();
    BookStoreResponse<List<NhaXuatBan>> layTCNXB();
    BookStoreResponse thongKeDoanhThuNam(int year);


}
