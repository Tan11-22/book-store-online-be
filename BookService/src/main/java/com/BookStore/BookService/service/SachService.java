package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.CardSach;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.model.Sach;

import java.util.List;

public interface SachService {
    BookStoreResponse<List<CardSach>> layDSSach(int start, int size);

    BookStoreResponse<ChiTietSachDTO> layChiTietSach(String isbn);

    BookStoreResponse<List<CardSach>> timSach(String search, int start, int size);
}
