package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.TacGiaDTO;
import com.BookStore.BookManageService.repository.TacGiaRepository;
import com.BookStore.BookManageService.service.TacGiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TacGiaServiceImpl implements TacGiaService {
    @Autowired
    private TacGiaRepository tacGiaRepository;

    @Override
    public int laySoLuongSangTac(int id) {
        return tacGiaRepository.laySoLuongSangTac(id);
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> xoaTacGia(int id) {
        if( laySoLuongSangTac(id) > 0) {
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Xoá tác giả thất bại!Cửa hàng có tồn tại sáng tác của tác giả này.")
                    .data(false)
                    .build();
        }
        try {
            tacGiaRepository.xoaTacGia(id);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Xoá tác giả thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: --- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Xoá tác giả thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> themTacGia(String ho, String ten) {
        try {
            tacGiaRepository.themTacGia(ho, ten);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Thêm tác giả thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: --- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Thêm tác giả thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> capNhatTacGia(int id, String ho, String ten) {
        try {
            tacGiaRepository.capNhatTacGia(id, ho, ten);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật tác giả thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: --- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Cập nhật tác giả thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    public BookStoreResponse<List<TacGiaDTO>> layDSTacGia() {
        List<Map<String, Object>> data = tacGiaRepository.layDSTacGia();
        return BookStoreResponse.<List<TacGiaDTO>>builder()
                .code(200)
                .status("Lấy danh sách tác giả thành công")
                .data(
                    data.stream().map(
                    map->mapTGtoDTO(map)).collect(Collectors.toList()))
                .build();
    }

    private TacGiaDTO mapTGtoDTO(Map<String, Object> data) {
        return TacGiaDTO.builder()
                .id((Integer) data.get("IDTACGIA"))
                .ho((String) data.get("HO"))
                .ten((String) data.get("TEN"))
                .soLuongSangTac((Integer) data.get("SANGTAC"))
                .build();
    }
}
