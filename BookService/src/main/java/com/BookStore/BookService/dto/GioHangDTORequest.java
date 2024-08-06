package com.BookStore.BookService.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangDTORequest {
    private List<GioHangDTO> gioHangList;
    private String diaChi;
    private Integer hinhThucThanhToan;
    private String tenDangNhap;
}
