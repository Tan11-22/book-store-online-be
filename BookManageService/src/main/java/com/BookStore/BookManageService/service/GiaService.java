package com.BookStore.BookManageService.service;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.CTGiaDTO;
import com.BookStore.BookManageService.dto.GiaDTO;
import com.BookStore.BookManageService.model.CTGiaSach;

import java.util.List;

public interface GiaService {
    BookStoreResponse<List<GiaDTO>> getSachVaGia(String search);
    BookStoreResponse<List<CTGiaDTO>> getCTGiaSach(String isbn);
    BookStoreResponse<Boolean> themGiaMoi(CTGiaSach data);
    BookStoreResponse<Boolean> capNhatGia(CTGiaSach data);
}
