package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TacGiaDTO {
    private Integer id;
    private String ho;
    private String ten;
    private Integer soLuongSangTac;
}
