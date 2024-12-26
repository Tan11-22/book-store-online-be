package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.*;
import com.BookStore.BookManageService.model.CTDonNhapSach;
import com.BookStore.BookManageService.model.DonNhapSach;
import com.BookStore.BookManageService.model.NhaXuatBan;
import com.BookStore.BookManageService.repository.CTDonNhapSachRepository;
import com.BookStore.BookManageService.repository.DonNhapSachRepository;
import com.BookStore.BookManageService.repository.NhaXuatBanRepository;
import com.BookStore.BookManageService.service.DonNhapSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DonNhapSachServiceImpl implements DonNhapSachService {

    @Autowired
    private DonNhapSachRepository donNhapSachRepository;

    @Autowired
    private NhaXuatBanRepository nhaXuatBanRepository;

    @Autowired
    private CTDonNhapSachRepository ctDonNhapSachRepository;

    @Override
    public BookStoreResponse<List<DonNhapSachDTO>> layALLDonNhapSach(String keyword) {
        List<Map<String, Object>> data = keyword.equals("")? donNhapSachRepository.layAllDonNhapSach():donNhapSachRepository.layAllDNSTK(keyword);
        List<DonNhapSachDTO> result = data.stream().map(map->mapDNStoDTO(map)).toList();
        for (DonNhapSachDTO x : result) {
            System.out.println(x.toString());
        }
        return BookStoreResponse.<List<DonNhapSachDTO>>builder()
                .code(200)
                .status("Lấy tất cả đơn nhập sách thành công!")
                .data(result).build();
    }

    @Override
    public BookStoreResponse<List<NhaXuatBan>> layALLNXB() {
        List<NhaXuatBan> result = nhaXuatBanRepository.findAll();
        return BookStoreResponse.<List<NhaXuatBan>>builder()
                .code(200)
                .status("Lấy tất cả nhà xuất bản thành công!")
                .data(result).build();
    }

    @Override
    public BookStoreResponse<List<SachDNDTO>> laySachNhapTheoNXB(String maNXB) {
        List<Map<String, Object>> data = donNhapSachRepository.layDSSachtheoNXB(maNXB);
        List<SachDNDTO> result = data.stream().map(map->mapSachtoDTO(map)).toList();
        return BookStoreResponse.<List<SachDNDTO>>builder()
                .code(200)
                .status("Lấy sách của nhà xuất bản thành công!")
                .data(result).build();
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> datNhapSach(DonNhapSachRequestDTO donNhapSachRequestDTO) {
        try {
            DonNhapSach x = donNhapSachRequestDTO.getDonNhapSach();
            donNhapSachRepository.save(x);
            SimpleDateFormat formatter2 = new SimpleDateFormat("MM-dd-yyyy");
            String ngayDat = formatter2.format(x.getNgayDat());
            int id = donNhapSachRepository.layIDDonNhapSach(x.getMaNhaXuatBan(),ngayDat);
            System.out.println(id);
            for(SachDNDTO y: donNhapSachRequestDTO.getCtDonNhapSachs()) {
                CTDonNhapSach a = mapDTOToEntity(y,id);
                ctDonNhapSachRepository.save(a);
            }
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Đặt nhập sách thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        System.out.println("ERROR-62: Cap nhat sach vao gio hang--- " + e.getMessage());
        return BookStoreResponse.<Boolean>builder()
                .code(201)
                .status("Đã có lỗi xảy ra!" +e.getMessage())
                .data(false)
                .build();
        }
//        return null;
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> huyDonNhapSach(int idDonNhap) {
        int SLPN = donNhapSachRepository.laySLPhieuNhapCuaDonNhap(idDonNhap);
        if(SLPN > 0) {
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Đơn đã lập phiếu nhập không thể huỷ!")
                    .data(false)
                    .build();
        }
        try {
            donNhapSachRepository.xoaCTDonNhapSach(idDonNhap);
            donNhapSachRepository.xoaDonNhapSach(idDonNhap);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Huỷ đơn thành công!")
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
    public BookStoreResponse<DonNhapSachResponseDTO> getChiTietDonTheoID(int id) {
        Map<String, Object> data = donNhapSachRepository.getDonNhapSachTheoId(id);
        DonNhapSachDTO don = mapDNStoDTO(data);
        List<Map<String, Object>> dataSach = donNhapSachRepository.getCTDonNhapSachTheoIdDon(id);
        List<SachDNDTO> sachs = dataSach.stream().map(map->mapSachtoDTO(map)).toList();
        DonNhapSachResponseDTO result = DonNhapSachResponseDTO.builder().donNhapSachDTO(don).sachs(sachs).build();
        return BookStoreResponse.<DonNhapSachResponseDTO>builder()
                .code(200)
                .status("Lấy chi tiết đơn nhập thành công!")
                .data(result).build();
    }

    @Override
    public BookStoreResponse<DonNhapSachResponseDTO> layChiTietDonTheoID(int id) {
        Map<String, Object> data = donNhapSachRepository.getDonNhapSachTheoId(id);
        DonNhapSachDTO don = mapDNStoDTO(data);
        List<Map<String, Object>> dataSach = donNhapSachRepository.layCTDonNhapSachTheoIdDon(id);
        List<SachDNDTO> sachs = dataSach.stream().map(map->mapSachtoDTO(map)).toList();
        DonNhapSachResponseDTO result = DonNhapSachResponseDTO.builder().donNhapSachDTO(don).sachs(sachs).build();
        return BookStoreResponse.<DonNhapSachResponseDTO>builder()
                .code(200)
                .status("Lấy chi tiết đơn nhập thành công!")
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
                .soLuongNhap(soLuong)
                .build();
    }


    private DonNhapSachDTO mapDNStoDTO(Map<String, Object> data) {
        return DonNhapSachDTO.builder()
                .idDonNhapSach((Integer) data.get("IDDONNHAPSACH"))
                .maNhaXuatBan((String) data.get("MANHAXUATBAN"))
                .maNhanVien((String) data.get("MANHANVIEN"))
                .ngayDat((Date) data.get("NGAYDAT"))
                .tenNhaXuatBan((String) data.get("TENNHAXUATBAN"))
                .daNhap((Integer) data.get("DANHAP"))
                .phaiNhap((Integer) data.get("PHAINHAP"))
                .build();
    }

    private CTDonNhapSach mapDTOToEntity(SachDNDTO data, int id) {
        return CTDonNhapSach.builder()
                .idDonNhapSach(id)
                .isbn(data.getIsbn())
                .soLuong(data.getSoLuong())
                .donGia(data.getGiaNhap())
                .build();
    }
}
