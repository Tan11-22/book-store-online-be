package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.CTDMSDTO;
import com.BookStore.BookManageService.dto.DonMuaSachDTO;
import com.BookStore.BookManageService.dto.SachDNDTO;
import com.BookStore.BookManageService.repository.DonMuaSachRepository;
import com.BookStore.BookManageService.service.DonMuaSachService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DonMuaSachServiceImpl implements DonMuaSachService {
    @Autowired
    private DonMuaSachRepository donMuaSachRepository;

    @Override
    @Transactional
    public BookStoreResponse huyDonMuaSach(Map<String, Object> data) {
        int idDon = (Integer) data.get("idDon");
        try {
            donMuaSachRepository.updateHuyDonMua(idDon);
            List<Map<String, Object>> listISBNSachMua = donMuaSachRepository.getISBNbyIdDon(idDon);
            for(Map<String, Object> x: listISBNSachMua) {
                int soLuongSachTrongKho = donMuaSachRepository.getSoLuongSach((String) x.get("ISBN"));
                donMuaSachRepository.updateSoLuongSach((String) x.get("ISBN"),soLuongSachTrongKho+((Integer)x.get("SOLUONG")));
            }
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Hoàn tác sách , huỷ đơn mua thành công.")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Xoá sach trong gio hang--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Huỷ đơn mua thất bại! " + e.getMessage())
                    .data(false)
                    .build();
        }
    }

    @Override
    public BookStoreResponse layDanhSachDMS(int type) {
        List<Map<String, Object>> data = donMuaSachRepository.layDSDonMuaSachQT(type);
        List<DonMuaSachDTO> result = data.stream().map(map -> mapDMSToDTO(map)).toList();
        return BookStoreResponse.builder()
                .code(200)
                .status("")
                .data(result).build();
    }

    private DonMuaSachDTO mapDMSToDTO(Map<String, Object> data) {
        return DonMuaSachDTO.builder()
                .idDonMuaSach((Integer) data.get("IDDONMUASACH"))
                .hoTen((String) data.get("HOTEN"))
                .sdt((String) data.get("SODIENTHOAI"))
                .email((String) data.get("EMAIL"))
                .ngayMua((Date) data.get("NGAYMUA"))
                .trangThaiThanhToan((Integer) data.get("TRANGTHAITHANHTOAN"))
                .diaChiGiao((String) data.get("DIACHIGIAO"))
                .phiVanChuyen((Integer) data.get("PHIVANCHUYEN"))
                .trangThaiDonHang((Integer) data.get("TRANGTHAIDONHANG"))
                .build();
    }
    @Override
    @Transactional
    public BookStoreResponse capNhatTrangThaiDMS(Map<String, Object> data) {
        int idDon = (Integer) data.get("idDon");
        String manv = (String) data.get("manv");
        try {
            donMuaSachRepository.updateTrangThaiDon_DMS(idDon, manv);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật trạng thái đơn mua sách thành công")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Huỷ đơn nhập--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Đã có lỗi xảy ra!")
                    .data(false)
                    .build();
        }

    }

    @Override
    @Transactional
    public BookStoreResponse capNhatTrangThaiGiaoDMS(Map<String, Object> data) {
        int idDon = (Integer) data.get("idDon");
        String manv = (String) data.get("manv");
        try {
            donMuaSachRepository.updateTrangThaiGiaoHang_DMS(idDon,manv);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật trạng thái giao hàng đơn mua sách thành công")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Huỷ đơn nhập--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(202)
                    .status("Đã có lỗi xảy ra!")
                    .data(false)
                    .build();
        }

    }

    @Override
    public BookStoreResponse layDSChiTietDMS(Map<String, Object> data) {
        int idDon = (Integer) data.get("idDon");
        List<Map<String, Object>> data1 = donMuaSachRepository.layCTDMS(idDon);
        List<CTDMSDTO> result = data1.stream().map(map -> mapCTDMSToDTO(map)).toList();
        return BookStoreResponse.builder()
                .code(200)
                .status("")
                .data(result).build();
    }

    private CTDMSDTO mapCTDMSToDTO(Map<String, Object> data) {
        return CTDMSDTO.builder()
                .ISBN((String) data.get("ISBN"))
                .tenSach((String) data.get("TENSACH"))
                .tenAnh((String) data.get("TENANH"))
                .soLuong((Integer) data.get("SOLUONG"))
                .donGia((Integer) data.get("DONGIA"))
                .build();
    }


}
