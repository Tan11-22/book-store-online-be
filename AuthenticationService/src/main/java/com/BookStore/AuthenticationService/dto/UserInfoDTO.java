package com.BookStore.AuthenticationService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {
    private String username;
    private String token;
    private String role;
    private String hoTen;
    private String hinhAnh;
    private String pictureGoogle = null;
}
