package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.*;
import com.BookStore.BookManageService.model.NhaXuatBan;

import java.util.List;

public interface DonNhapSachService {
    BookStoreResponse<List<DonNhapSachDTO>> layALLDonNhapSach();
    BookStoreResponse<List<NhaXuatBan>> layALLNXB();
    BookStoreResponse<List<SachDNDTO>> laySachNhapTheoNXB(String maNXB);
    BookStoreResponse<Boolean> datNhapSach(DonNhapSachRequestDTO donNhapSachRequestDTO);
    BookStoreResponse<Boolean> huyDonNhapSach(int idDonNhap);
    BookStoreResponse<DonNhapSachResponseDTO> getChiTietDonTheoID(int id);
}
