package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.BinhLuanDTO;
import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.model.BinhLuanEntity;
import com.BookStore.BookService.repository.BinhLuanRepository;
import com.BookStore.BookService.service.BinhLuanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BinhLuanImpl implements BinhLuanService {

    @Autowired
    private BinhLuanRepository binhLuanRepository;

    @Override
    public BookStoreResponse binhLuan(Map<String, Object> data) {
        System.out.println(data.toString());
        try {
            BinhLuanEntity binhLuanEntity = BinhLuanEntity.builder()
                    .isbn((String) data.get("maSach"))
                    .tenDangNhap((String) data.get("user"))
                    .diem((Integer) data.get("diem"))
                    .noiDung((String) data.get("binhLuan"))
                    .ngay(LocalDateTime.now())
                    .build();
            binhLuanRepository.save(binhLuanEntity);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Xoá sach trong gio hang--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(503)
                    .status("Lưu Bình luận thất bại" + e.getMessage())
                    .data(false)
                    .build();
        }
        return BookStoreResponse.<Boolean>builder()
                .code(201)
                .data(true)
                .status("Tạo bình luận thành công!")
                .build();
    }

    @Override
    public BookStoreResponse danhSachBinhLuanCuaSach(Map<String, Object> data) {
        String isbn = String.valueOf(data.get("isbn"));
        int start = (Integer) data.get("start");
        int size = (Integer) data.get("size");
        return BookStoreResponse.<List<BinhLuanDTO>>builder()
                .code(201)
                .data(mapToBLDTO(isbn, start, size))
                .status("Tạo bình luận thành công!")
                .build();
    }


    private List<BinhLuanDTO> mapToBLDTO(String isbn, int start, int size) {
        List<Map<String,Object>> data = binhLuanRepository.layDanhSachBinhLuanCuaSach(isbn, start, size);

        return data.stream().map(map ->
                BinhLuanDTO.builder()
                        .idBinhLuan((Integer) map.get("IDBINHLUAN"))
                        .tenDangNhap((String) map.get("TENDANGNHAP"))
                        .isbn((String) map.get("ISBN"))
                        .noiDung((String) map.get("NOIDUNG"))
                        .diem((Integer) map.get("DIEM"))
                        .ngay(chuyenDoiNgay((Date) map.get("NGAY")))
                        .hoTen((String) map.get("HOTEN"))
                        .hinhAnh((String) map.get("HINHANH"))
                        .build()
                ).collect(Collectors.toList());
    }
    private String chuyenDoiNgay(Date ngay) {
        LocalDateTime localDateTime = ngay.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        // Định dạng đầu ra
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH 'giờ' mm 'phút ngày' dd-MM-yyyy");

        // Chuyển đổi sang chuỗi với định dạng mới

        return  localDateTime.format(outputFormatter);
    }

    @Override
    public BookStoreResponse soLuongBinhLuan(String isbn) {
        int soLuongBinhLuan = binhLuanRepository.demSoLuongBinhLuan(isbn);
        return BookStoreResponse.<Integer>builder()
                .status("Lấy số lượng bình luận thành công!")
                .data(soLuongBinhLuan)
                .code(200).build();
    }
}
