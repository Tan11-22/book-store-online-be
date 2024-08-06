package com.BookStore.AuthenticationService.Service.Impl;

import com.BookStore.AuthenticationService.Service.TaiKhoanService;
import com.BookStore.AuthenticationService.dto.BookStoreResponse;
import com.BookStore.AuthenticationService.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.Map;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired EmailService emailService;

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

    @Override
    public BookStoreResponse<Boolean> thayDoiMatKhau(String username, String password, String newpassword) {
        Map<String, Object> user = taiKhoanRepository.loadUser(username);
        PasswordEncoder encode = new BCryptPasswordEncoder();
        if (!(encode.matches(password, (String) user.get("MATKHAU")))) {
            return  BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Mật khẩu cũ không chính xác không chính xác!")
                    .data(false).build();
        }
        try {
//            PasswordEncoder encode = new BCryptPasswordEncoder();
            String password1 = encode.encode(newpassword);
            taiKhoanRepository.doiMatKhau(username,password1);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật mật khẩu thành công!")
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
    @Transactional
    public BookStoreResponse quenMatKhau(String email, String username) {
        Map<String, Object> user = taiKhoanRepository.loadUser(username);
        if (user.get("TENDANGNHAP") == null) {
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Tài khoản không tồn tại!")
                    .data(false).build();
        }
        String email1 = getEmail(user);
        if (email1.equals(email)) {
            try {
                String resetPassword = emailService.generateCode();
                PasswordEncoder encode = new BCryptPasswordEncoder();
                String password1 = encode.encode(resetPassword);
                taiKhoanRepository.doiMatKhau(username,password1);
                System.out.println(resetPassword);
                emailService.sendEmail(email1, "Mật khẩu mới: " + resetPassword);
                return BookStoreResponse.<Boolean>builder()
                        .code(200)
                        .status("Mật khẩu mới đã được gửi đến email của bạn!")
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
        return BookStoreResponse.<Boolean>builder()
                .code(202)
                .status("Địa chỉ email không chính xác!")
                .data(false).build();
    }

    public String getEmail(Map<String, Object> user) {

        if (((String) user.get("TENQUYEN")).equals("KHACHHANG")) {
            return taiKhoanRepository.getEmailKH((String) user.get("TENDANGNHAP"));
        } else {
            return taiKhoanRepository.getEmailNV((String) user.get("TENDANGNHAP"));
        }
    }
}
