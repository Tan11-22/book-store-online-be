package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheLoaiDTO {
    private Integer id;
    private String tenTheLoai;
    private Integer soLuongSach;
}
