package com.BookStore.AuthenticationService.Service.Impl;

import com.BookStore.AuthenticationService.Service.AuthService;
import com.BookStore.AuthenticationService.jwt.JwtTokenProvider;
import com.BookStore.AuthenticationService.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(String tenDangNhap, String matKhau) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(tenDangNhap, matKhau));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}
