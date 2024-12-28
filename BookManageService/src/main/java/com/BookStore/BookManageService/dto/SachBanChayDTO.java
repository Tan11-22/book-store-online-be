package com.BookStore.BookManageService.dto;

import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.model.TheLoai;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SachBanChayDTO {
    private String isbn;
    private String tenSach;
    private String khuonKho;
    private Integer soTrang;
    private Integer trongLuong;
    private String moTa;
    private Integer soLuongCon;
    private Long soLuongBan;
    private Long thanhTien;
    private String maNhaXuatBan;
    private String tenNhaXuatBan;
    private List<TacGia> tacGias;
    private List<TheLoai> theLoais;
}
