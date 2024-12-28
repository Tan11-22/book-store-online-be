package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.NhaXuatBanDTO;
import com.BookStore.BookManageService.dto.TacGiaDTO;
import com.BookStore.BookManageService.repository.NhaXuatBanRepository;
import com.BookStore.BookManageService.service.NhaXuatBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NhaXuatBanServiceImpl implements NhaXuatBanService {

    @Autowired
    private NhaXuatBanRepository nhaXuatBanRepository;

    @Override
    @Transactional
    public BookStoreResponse<Boolean> xoaNhaXuatBan(String ma) {
        if( nhaXuatBanRepository.laySLSachCuaNXB(ma) > 0) {
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Đã có sách của nhà xuất bản này trong hệ thống, không thể xoá!")
                    .data(false)
                    .build();
        }
        try {
            nhaXuatBanRepository.xoaNhaXuatBan(ma);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Xoá nhà xuất bản thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: --- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Xoá nhà xuất bản thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> themNhaXuatBan(String ma, String ten, String chuSoHuu) {
        try {
            nhaXuatBanRepository.themNhaXuatBan(ma, ten, chuSoHuu);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Thêm nhà xuất bản thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: --- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Thêm nhà xuất bản thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> capNhatNhaXuatBan(String ma, String ten, String chuSoHuu) {
        try {
            nhaXuatBanRepository.capNhatNhaXuatBan(ma, ten, chuSoHuu);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật nhà xuất bản thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: --- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Cập nhật nhà xuất bản thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    public BookStoreResponse<List<NhaXuatBanDTO>> layDSTacGia() {
        List<Map<String, Object>> data = nhaXuatBanRepository.layDSNXB();
        return BookStoreResponse.<List<NhaXuatBanDTO>>builder()
                .code(200)
                .status("Lấy danh sách tác giả thành công")
                .data(
                        data.stream().map(
                                map->mapNXBtoDTO(map)).collect(Collectors.toList()))
                .build();
    }
    private NhaXuatBanDTO mapNXBtoDTO(Map<String, Object> data) {
        return NhaXuatBanDTO.builder()
                .maNhaXuatBan((String) data.get("MANHAXUATBAN"))
                .tenNhaXuatBan((String) data.get("TENNHAXUATBAN"))
                .chuSoHuu((String) data.get("CHUSOHUU"))
                .soSach((Integer) data.get("SOSACH"))
                .build();
    }
}
