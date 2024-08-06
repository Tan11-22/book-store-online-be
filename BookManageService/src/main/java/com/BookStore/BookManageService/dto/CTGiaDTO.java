package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTGiaDTO {
    private Integer idCTGia;
    private String tenGia;
    private Integer gia;
    private Date ngayApDung;
    private Date ngayKetThuc;
}
