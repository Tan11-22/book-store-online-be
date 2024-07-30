package com.BookStore.AuthenticationService.Service.Impl;

import com.BookStore.AuthenticationService.Service.TaiKhoanService;
import com.BookStore.AuthenticationService.dto.BookStoreResponse;
import com.BookStore.AuthenticationService.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public BookStoreResponse<Boolean> dangKyTaiKhoan(String tenDangNhap, String matKhau, String email, String ho, String ten, String gioiTinh, String diaChi, Date ngaySinh, String soDienThoai) {
        if(taiKhoanRepository.checkTDN(tenDangNhap)==1) {
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Tên đăng nhập đã tồn tại!")
                    .data(false)
                    .build();
        }

        if(taiKhoanRepository.checkEmail(email)==1) {
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Email đã tồn tại!")
                    .data(false)
                    .build();
        }
        try {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(matKhau);
            taiKhoanRepository.taoTaiKhoan(tenDangNhap,password,2);
            taiKhoanRepository.taoKhachHang(tenDangNhap,email,ho,ten,gioiTinh.equals(0)?false:true,diaChi,ngaySinh,soDienThoai);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Đăng ký tài khoản thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Đang ký tài khoản--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(203)
                    .status("Đã có lỗi xảy ra!")
                    .data(false)
                    .build();
        }
    }

    @Override
    public String lauQuyenCuaUser(String tenDangNhap) {
        return taiKhoanRepository.layQuyenCuaTK(tenDangNhap);
    }
}
