package com.BookStore.AuthenticationService.repository;

import com.BookStore.AuthenticationService.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
}
