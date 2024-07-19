package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.repository.TheLoaiRepository;
import com.BookStore.BookManageService.service.TheLoaiService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class TheLoaiServiceImpl implements TheLoaiService {
    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Override
    public int laySoLuongSach(int id) {
        return theLoaiRepository.laySoLuongSach(id);
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> xoaTheLoai(int id) {
        if(laySoLuongSach(id) > 0) {
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Xoá thể loại thất bại! Có sách thuộc thể loại này trong cửa hàng")
                    .data(false)
                    .build();
        }
        try {
            theLoaiRepository.xoaTheLoai(id);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Xoá thể loại thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Xoá thể loại thất bại!")
                    .data(false)
                    .build();
        }

    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> themTheLoai(String ten) {
        try {
            theLoaiRepository.themTheLoai(ten);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Thêm thể loại thành công!")
                    .data(true)
                    .build();

        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Thêm thể loại thất bại!")
                    .data(false)
                    .build();
        }

    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> capNhatTheLoai(int id, String ten) {
        try {
            theLoaiRepository.capNhatTheLoai(id, ten);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật thể loại thành công!")
                    .data(true)
                    .build();

        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Cập nhật thể loại thất bại!")
                    .data(false)
                    .build();
        }
    }
}
