package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.BookStoreResponse;
import com.BookStore.BookManageService.dto.CTGiaDTO;
import com.BookStore.BookManageService.dto.DonNhapSachDTO;
import com.BookStore.BookManageService.dto.GiaDTO;
import com.BookStore.BookManageService.model.CTGiaSach;
import com.BookStore.BookManageService.model.Gia;
import com.BookStore.BookManageService.repository.CtGiaSachRepository;
import com.BookStore.BookManageService.repository.GiaRepository;
import com.BookStore.BookManageService.repository.SachRepository;
import com.BookStore.BookManageService.service.GiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GiaServiceImpl implements GiaService {

    @Autowired
    private GiaRepository giaRepository;

    @Autowired
    private CtGiaSachRepository ctGiaSachRepository;

    @Override
    public BookStoreResponse<List<GiaDTO>> getSachVaGia(String search) {
        List<Map<String, Object>> data = giaRepository.getSachVaGia(search);
        List<GiaDTO> result = data.stream().map(map->mapGiatoDTO(map)).toList();
        return BookStoreResponse.<List<GiaDTO>>builder()
                .code(200)
                .status("Lấy danh sách sách thành công!")
                .data(result).build();
    }

    @Override
    public BookStoreResponse<List<CTGiaDTO>> getCTGiaSach(String isbn) {
        List<Map<String, Object>> data = giaRepository.getCTGiaSach(isbn);
        List<CTGiaDTO> result = data.stream().map(map->mapCTGiatoDTO(map)).toList();
        return BookStoreResponse.<List<CTGiaDTO>>builder()
                .code(200)
                .status("Lấy danh sách chi tiết giá sách thành công!")
                .data(result).build();
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> themGiaMoi(CTGiaSach data) {
        System.out.println(data);
        int kiemTraTonTai = giaRepository.kiemTraTonTaiGiaSach(data.getIdGia(), data.getNgayApDung(), data.getNgayKetThuc(), data.getIsbn());
        if(kiemTraTonTai > 0) {
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Đã tồn tại loại giá được áp dụng từ " + data.getNgayApDung()+" đến "+ data.getNgayKetThuc()+"!")
                    .data(false)
                    .build();
        }
        try {
            ctGiaSachRepository.save(data);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Thêm giá mới thành công!")
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
    public BookStoreResponse<Boolean> capNhatGia(CTGiaSach data) {
        try {
            ctGiaSachRepository.capNhatGiaSach(data.getIdCTGiaSach(),data.getGia(),data.getNgayApDung(), data.getNgayKetThuc());
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật giá thành công!")
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

    private GiaDTO mapGiatoDTO(Map<String, Object> data) {
        return GiaDTO.builder()
                .isbn((String) data.get("ISBN"))
                .tenSach((String) data.get("TENSACH"))
                .tenAnh((String) data.get("FILENAME"))
                .giaNhap((Integer) data.get("GIANHAP"))
                .giaBan((Integer) data.get("GIABAN"))
                .giaKhuyenMai((Integer) data.get("GIAKHUYENMAI"))
                .build();
    }

    private CTGiaDTO mapCTGiatoDTO(Map<String, Object> data) {
        return CTGiaDTO.builder()
                .idCTGia((Integer) data.get("IDCTGIASACH"))
                .tenGia((String) data.get("TENGIA"))
                .gia((Integer) data.get("GIA"))
                .ngayApDung((Date) data.get("NGAYAPDUNG"))
                .ngayKetThuc((Date) data.get("NGAYKETTHUC"))
                .build();
    }
}
