package com.BookStore.AuthenticationService.controller;

import com.BookStore.AuthenticationService.Service.AuthService;
import com.BookStore.AuthenticationService.Service.KhachHangService;
import com.BookStore.AuthenticationService.Service.TaiKhoanService;
import com.BookStore.AuthenticationService.dto.BookStoreResponse;
import com.BookStore.AuthenticationService.jwt.JwtTokenProvider;
import com.BookStore.AuthenticationService.model.KhachHang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/authentication-service/")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private KhachHangService khachHangService;

    @PostMapping("login")
    public Map<String, String> testLogin(@RequestBody Map<String, String> user) {
        System.out.println("us: "+ user.get("tendangnhap"));
        System.out.println("pass: "+ user.get("matkhau"));
        String result = authService.login(user.get("tendangnhap"), user.get("matkhau"));
//        String role = taiKhoanService.getRoleUser(user.get("username"));
        Map<String, String> result1 = new HashMap<>();
        result1.put("token", result);
        result1.put("username", user.get("tendangnhap"));
        result1.put("role", taiKhoanService.lauQuyenCuaUser(user.get("tendangnhap")));
        return result1;
    }


    @PostMapping("dang-ky")
    public BookStoreResponse<Boolean> registerUser(
            @RequestParam("tenDangNhap") String tenDangNhap,
            @RequestParam("matKhau") String matKhau,
            @RequestParam("email") String email,
            @RequestParam("ho") String ho,
            @RequestParam("ten") String ten,
            @RequestParam("gioiTinh") String gioiTinh,
            @RequestParam("diaChi") String diaChi,
            @RequestParam("ngaySinh") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngaySinh,
            @RequestParam("soDienThoai") String soDienThoai
    ) {
        return taiKhoanService.dangKyTaiKhoan(tenDangNhap,matKhau,email,ho,ten,gioiTinh,diaChi,ngaySinh,soDienThoai);
    }

    @GetMapping("get-info")
    public BookStoreResponse<KhachHang> getInfoKhachHang(@RequestParam("tdn") String tenDangNhap) {
        return khachHangService.getInfoKhachHang(tenDangNhap);
    }

    @PostMapping("thay-doi-mat-khau")
    public BookStoreResponse<Boolean> thayDoiMatKhau(@RequestBody Map<String, String> userInfo) {
        System.out.println(userInfo);
        return taiKhoanService.thayDoiMatKhau(userInfo.get("username"), userInfo.get("password"),
                userInfo.get("newpassword"));
    }

    @PostMapping("valid")
    public Map<String, Boolean> valid(@RequestBody Map<String, String> token) {
        System.out.println("check valid token");
        Boolean isValid = jwtTokenProvider.validateToken(token.get("token"));
        System.out.println(isValid);
        System.out.println("_-_-_-_-_");
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", isValid);
        return map;
    }

    @GetMapping("quen-mat-khau")
    public BookStoreResponse quenMatKhau(@RequestParam("username") String username,
                                   @RequestParam("email") String email) {
        return taiKhoanService.quenMatKhau(email, username);
    }
}
