package com.BookStore.BookService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BinhLuanDTO {
    private Integer idBinhLuan;

    private String tenDangNhap;

    private  String isbn;

    private String noiDung;

    private Integer diem;

    private Date ngay;

    private String hoTen;

    private String hinhAnh;
}
