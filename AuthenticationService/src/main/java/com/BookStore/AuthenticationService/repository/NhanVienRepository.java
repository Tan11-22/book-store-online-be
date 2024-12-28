package com.BookStore.AuthenticationService.repository;

import com.BookStore.AuthenticationService.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
}
