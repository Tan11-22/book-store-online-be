package com.BookStore.AuthenticationService.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.BookStore.AuthenticationService.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String tenDangNhap) throws UsernameNotFoundException {
        Map<String, Object> taiKhoan = taiKhoanRepository.loadUser(tenDangNhap);
        if (taiKhoan == null) {
            return (UserDetails) new UsernameNotFoundException("User not exists by Username");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority((String) taiKhoan.get("TENQUYEN"));
        return new org.springframework.security.core.userdetails.User(
                tenDangNhap,
                (String) taiKhoan.get("MATKHAU"),
                Collections.singleton(authority));
    }
}
