package com.BookStore.BookService.dto;


import com.BookStore.BookService.model.HinhAnh;
import com.BookStore.BookService.model.TacGia;
import com.BookStore.BookService.model.TheLoai;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChiTietSachDTO {

    private SachDTO sachDTO;

    private List<TacGia> tacGias;

    private  List<TheLoai> theLoais;

    private List<HinhAnh> hinhAnhs;

    private List<BinhLuanDTO> binhLuanDTOS;
}
