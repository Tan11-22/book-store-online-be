package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.*;
import com.BookStore.BookService.model.DonMuaSach;
import com.BookStore.BookService.repository.DonMuaSachRepository;
import com.BookStore.BookService.repository.GioHangRepository;
import com.BookStore.BookService.service.DonMuaSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DonMuaSachServiceImpl implements DonMuaSachService {
    @Autowired
    private DonMuaSachRepository donMuaSachRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    @Transactional
    public BookStoreResponse<?> datMuaSach(GioHangDTORequest gioHangDTORequest) {
        // kiểm tra sách đủ số lượng ?
        for(GioHangDTO x : gioHangDTORequest.getGioHangList()) {
            int slMax = donMuaSachRepository.laySoLuongSachDangCo(x.getIsbn());
            if (x.getSoLuong()>slMax) {
                Map<String,Object> response = new HashMap<>();
                response.put("idGioHang",x.getIdGioHang());
                response.put("soLuong",slMax);
                return BookStoreResponse.<Map<String, Object>> builder()
                        .code(201)
                        .status(x.getTenSach()+" chỉ còn "+ String.valueOf(slMax)+" cuốn!")
                        .data(response)
                        .build();
            }
        }

        // thực hiện insert donmua nếu thanh toán trực tiếp
            try {
                donMuaSachRepository.themDonMuaSach(gioHangDTORequest.getTenDangNhap(),
                        gioHangDTORequest.getHinhThucThanhToan()==0?1:2, // trạng thái = 1: chưa thanh toán,2: đã thanh toán.
                        gioHangDTORequest.getDiaChi() );
                for(GioHangDTO x : gioHangDTORequest.getGioHangList()) {
                    donMuaSachRepository.themSachVaoCTDonMua(
                            x.getIsbn(),
                            gioHangDTORequest.getTenDangNhap(),
                            x.getSoLuong(),
                            x.getGiaGiam() != null ? x.getGiaGiam():x.getGiaBan()
                            );
                    gioHangRepository.xoaSachTrongGioHang(x.getIdGioHang());
                    int soLuongSachTrongKho = donMuaSachRepository.getSoLuongSach(x.getIsbn());
                    donMuaSachRepository.updateSoLuongSach(x.getIsbn(),soLuongSachTrongKho-x.getSoLuong());
                }
                return BookStoreResponse.<Boolean>builder()
                        .code(200)
                        .status("Đặt mua thành công! ")
                        .data(true)
                        .build();
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                System.out.println("ERROR-62: Xoá sach trong gio hang--- " + e.getMessage());
                return BookStoreResponse.<Boolean>builder()
                        .code(202)
                        .status("Đặt mua thất bại! " + e.getMessage())
                        .data(false)
                        .build();
            }



    }

    @Override
    public BookStoreResponse<List<DonMuaSachDTO>> layThongTinDatMua(String tenDangNhap) {
        List<DonMuaSach> data = donMuaSachRepository.layDonMuaHang(tenDangNhap);
        List<DonMuaSachDTO> result = data.stream().map(map->mapDMStoDTO(map)).toList();
        return BookStoreResponse.<List<DonMuaSachDTO>>builder()
                .code(200)
                .status("Lấy danh sách đơn mua sách thành công!")
                .data(result).build();
    }

    private DonMuaSachDTO mapDMStoDTO(DonMuaSach data) {
        List<Map<String, Object>> dataCTDon = donMuaSachRepository.layChiTietDonMuaHang(data.getIdDonMuaSach());
        List<CTDonMuaSachDTO> sachs = dataCTDon.stream().map(map->mapCTDMSToDTO(map)).toList();
        return DonMuaSachDTO.builder()
                .donMuaSach(data)
                .sachs(sachs)
                .build();
    }
    private CTDonMuaSachDTO mapCTDMSToDTO(Map<String, Object> data) {
        return CTDonMuaSachDTO.builder()
                .idCTDonMuaSach((Integer) data.get("IDCTDONMUASACH"))
                .idDonMuaSach((Integer) data.get("IDDONMUASACH"))
                .isbn((String) data.get("ISBN"))
                .soLuong((Integer) data.get("SOLUONG"))
                .donGia((Integer) data.get("DONGIA"))
                .tenSach((String) data.get("TENSACH")).build();
    }
}
