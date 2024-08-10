package com.BookStore.BookManageService.dto;


import com.BookStore.BookManageService.model.PhieuNhap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTPNDTO {
    private PhieuNhap phieuNhap;
    private List<SachDNDTO> sachs;
}
