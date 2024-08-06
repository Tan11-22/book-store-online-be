package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.DonNhapSachRequestDTO;
import com.BookStore.BookManageService.dto.SachDNDTO;
import com.BookStore.BookManageService.model.CTDonNhapSach;
import com.BookStore.BookManageService.model.DonNhapSach;
import com.BookStore.BookManageService.model.PhieuNhap;
import com.BookStore.BookManageService.repository.PhieuNhapRepository;
import com.BookStore.BookManageService.service.PhieuNhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PhieuNhapServiceImpl  implements PhieuNhapService {
    @Autowired
    private PhieuNhapRepository phieuNhapRepository;

    @Override
    @Transactional
    public BookStoreResponse<Boolean> taoPhieuNhap(DonNhapSachRequestDTO donNhapSachRequestDTO) {
        try {
            DonNhapSach x = donNhapSachRequestDTO.getDonNhapSach();
            phieuNhapRepository.taophieuNhap(x.getMaNhanVien(),x.getIdDonNhapSach());
            int soPN = phieuNhapRepository.getSoPN(x.getMaNhanVien(),x.getIdDonNhapSach());
            for(SachDNDTO y: donNhapSachRequestDTO.getCtDonNhapSachs()) {
                phieuNhapRepository.insertCTPN(soPN,y.getIsbn(),y.getSoLuong(),y.getGiaNhap());
                int soLuongSach = phieuNhapRepository.getSoLuongSach(y.getIsbn()) + y.getSoLuong();
                phieuNhapRepository.updateSoLuongSach(y.getIsbn(),soLuongSach);
            }
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Tạo phiếu nhập thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Cap nhat sach vao gio hang--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Đã có lỗi xảy ra!")
                    .data(false)
                    .build();
        }
    }

    @Override
    public BookStoreResponse getAllPhieuNhap() {
        List<PhieuNhap> result = phieuNhapRepository.findAll();
        return BookStoreResponse.<List<PhieuNhap>>builder()
                .code(200)
                .status("Lấy danh sach phiếu nhập thành công!")
                .data(result).build();
    }
}
