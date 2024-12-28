package com.BookStore.BookManageService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonNhapSachResponseDTO {
    private DonNhapSachDTO donNhapSachDTO;
    private List<SachDNDTO> sachs;
}
