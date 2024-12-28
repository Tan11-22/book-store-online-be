package com.BookStore.BookManageService.dto;

import com.BookStore.BookManageService.model.DonNhapSach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonNhapSachRequestDTO {
    private DonNhapSach donNhapSach;
    private List<SachDNDTO> ctDonNhapSachs;
}
