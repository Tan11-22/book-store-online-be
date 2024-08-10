package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.*;
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
import java.util.Map;

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

    @Override
    public BookStoreResponse getCTPN(int soPhieuNhap) {
        PhieuNhap data = phieuNhapRepository.findById(soPhieuNhap).orElse(null);
        System.out.println(data);
        List<Map<String, Object>> dataSach = phieuNhapRepository.getCTPhieuNhapTheoSPN(soPhieuNhap);
        List<SachDNDTO> sachs = dataSach.stream().map(map->mapSachtoDTO(map)).toList();
        CTPNDTO result = CTPNDTO.builder().phieuNhap(data).sachs(sachs).build();
        return BookStoreResponse.<CTPNDTO>builder()
                .code(200)
                .status("Lấy chi tiết phiếu nhập thành công!")
                .data(result).build();
    }

    private SachDNDTO mapSachtoDTO(Map<String, Object> data) {
        int soLuong = data.get("SOLUONG")!=null ? (Integer) data.get("SOLUONG") : 1;
        return SachDNDTO.builder()
                .isbn((String) data.get("ISBN"))
                .tenSach((String) data.get("TENSACH"))
                .giaNhap((Integer) data.get("GIANHAP"))
                .tenAnh((String) data.get("TENANH"))
                .soLuong(soLuong)
                .build();
    }
}
