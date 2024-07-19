package com.BookStore.BookService.service;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.model.Sach;

import java.util.List;

public interface SachService {
    List<Sach> layDSSach(int start, int size);

    BookStoreResponse<ChiTietSachDTO> layChiTietSach(String isbn);

}
