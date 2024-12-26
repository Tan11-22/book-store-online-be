package com.BookStore.BookManageService.dto;

import com.BookStore.BookManageService.model.HinhAnh;
import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.model.TheLoai;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SachDTO {
    private String isbn;
    private String tenSach;
    private String khuonKho;
    private Integer soTrang;
    private Integer trongLuong;
    private String moTa;
    private Integer soLuong;
    private Integer soLuongNhap;
    private Integer soLuongBan;
    private String maNhaXuatBan;
    private String tenNhaXuatBan;
    private List<TacGia> tacGias;
    private List<TheLoai> theLoais;
    private List<HinhAnh> hinhAnhs;
}
