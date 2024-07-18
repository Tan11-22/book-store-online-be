package com.BookStore.BookService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String maNhaXuatBan;

    private String tenNhaXuatBan;

    private Integer giaBan;

    private Integer giaGiam;

    private Integer soBinhLuan;

    private Integer tongDiem;

}
