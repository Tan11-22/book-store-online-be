package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.CardSach;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.dto.TacGiaDTO;
import com.BookStore.BookService.model.Sach;
import com.BookStore.BookService.model.TacGia;
import com.BookStore.BookService.model.TheLoai;

import java.util.List;
import java.util.Map;

public interface SachService {
    BookStoreResponse<List<CardSach>> layDSSach(int start, int size);

    BookStoreResponse<ChiTietSachDTO> layChiTietSach(String isbn);

    BookStoreResponse<List<CardSach>> timSach(String search, int start, int size);
    BookStoreResponse demSLSachTimRa(String search);

    BookStoreResponse layDSSachBanChay(int start, int size);

    BookStoreResponse<List<CardSach>> timSach1(Map<String, Object> data);
    BookStoreResponse<Integer> countTimSach1(Map<String, Object> data);

    BookStoreResponse<List<TacGiaDTO>> layTCTG();
    BookStoreResponse<List<TheLoai>> layTCTL();

    BookStoreResponse<List<CardSach>> getSachCungTheLoai(String isbn);
}
