package com.BookStore.BookService.dto;

import com.BookStore.BookService.model.DonMuaSach;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonMuaSachDTO {
    private DonMuaSach donMuaSach;
    private List<CTDonMuaSachDTO> sachs;
    private boolean choPhepHuy;
}
