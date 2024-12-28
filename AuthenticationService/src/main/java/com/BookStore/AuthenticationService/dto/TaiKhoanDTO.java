package com.BookStore.AuthenticationService.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaiKhoanDTO {
    private String tenDangNhap;
    private Boolean trangThai;
    private String ho;
    private String ten;
    private String email;
    private String tenQuyen;
}
