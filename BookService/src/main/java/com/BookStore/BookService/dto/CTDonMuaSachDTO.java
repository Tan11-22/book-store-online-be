package com.BookStore.BookService.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTDonMuaSachDTO {
    private Integer idCTDonMuaSach;
    private Integer idDonMuaSach;
    private String isbn;
    private Integer soLuong;
    private Integer donGia;
    private String tenSach;
}
