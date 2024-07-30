package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.*;
import com.BookStore.BookService.model.HinhAnh;
import com.BookStore.BookService.model.Sach;
import com.BookStore.BookService.model.TacGia;
import com.BookStore.BookService.model.TheLoai;
import com.BookStore.BookService.repository.SachRepository;
import com.BookStore.BookService.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SachServiceImpl implements SachService {
    @Autowired
    private SachRepository sachRepository;


    // query thông tin sách dạng card dùng cho trang chủ , thể loại , tìm kiếm
    @Override
    public BookStoreResponse<List<CardSach>> layDSSach(int start, int size) {
        List<Map<String, Object>> data = sachRepository.layDSSach(start, size);
        List<CardSach> result = data.stream().map(map -> mapSachToCard(map)).toList();
        return BookStoreResponse.<List<CardSach>>builder()
                .code(200)
                .status("Lấy danh sách card sách thành công!")
                .data(result).build();
    }

    private CardSach mapSachToCard(Map<String, Object> data) {
        return CardSach.builder()
                .isbn((String) data.get("ISBN"))
                .tenSach((String) data.get("TENSACH"))
                .tenTacGia((String) data.get("TENTACGIA"))
                .giaBan((Integer) data.get("GIABAN"))
                .giaGiam((Integer) data.get("GIAGIAM"))
                .tenAnh((String) data.get("TENANH"))
                .build();
    }


    // những câu query để lấy thông tin chi tiết 1 cuốn sách
    @Override
    public BookStoreResponse<ChiTietSachDTO> layChiTietSach(String isbn) {
        ChiTietSachDTO chiTietSachDTO = taoDoiTuongChiTietSach(isbn);
        BookStoreResponse<ChiTietSachDTO> chiTietSachDTOBookStoreResponse = BookStoreResponse.<ChiTietSachDTO>builder()
                .code(200)
                .status("Lấy thông tin chi tiết của sách thành công!")
                .data(chiTietSachDTO)
                .build();
        return chiTietSachDTOBookStoreResponse;
    }

    @Override
    public BookStoreResponse<List<CardSach>> timSach(String search, int start, int size) {
        List<Map<String, Object>> data = sachRepository.timSach(search, start, size);
        if(data.size() == 0) {
            return BookStoreResponse.<List<CardSach>>builder()
                    .code(201)
                    .status("Không tìm thấy sách phù hợp!")
                    .data(null).build();
        }
        List<CardSach> result = data.stream().map(map -> mapSachToCard(map)).toList();
        return BookStoreResponse.<List<CardSach>>builder()
                .code(200)
                .status("Lấy danh sách card sách thành công!")
                .data(result).build();
    }

    private ChiTietSachDTO taoDoiTuongChiTietSach(String isbn) {
        SachDTO sachDTO = mapToDTO(sachRepository.layChiTietSach(isbn));
        List<HinhAnh> hinhAnhs = mapObToHA(isbn);
        List<TacGia> tacGias = mapObToTG(isbn);
        List<TheLoai> theLoais = mapObToTL(isbn);
        List<BinhLuanDTO> binhLuanDTOS = mapToBLDTO(isbn);

        return ChiTietSachDTO.builder()
                .sachDTO(sachDTO)
                .hinhAnhs(hinhAnhs)
                .tacGias(tacGias)
                .theLoais(theLoais)
                .binhLuanDTOS(binhLuanDTOS)
                .build();
    }

    private SachDTO mapToDTO(Map<String, Object> data) {
        return SachDTO.builder()
                .isbn((String) data.get("ISBN"))
                .tenSach((String) data.get("TENSACH"))
                .khuonKho((String) data.get("KHUONKHO"))
                .soTrang((Integer) data.get("SOTRANG"))
                .trongLuong((Integer) data.get("TRONGLUONG"))
                .moTa((String) data.get("MOTA"))
                .soLuong((Integer) data.get("SOLUONG"))
                .maNhaXuatBan((String) data.get("MANHAXUATBAN"))
                .tenNhaXuatBan((String) data.get("TENNHAXUATBAN"))
                .giaBan((Integer) data.get("GIABAN"))
                .giaGiam((Integer) data.get("GIAGIAM"))
                .soBinhLuan((Integer) data.get("SOBINHLUAN"))
                .tongDiem((Integer) data.get("TONGDIEM"))
                .build();
    }

    private List<BinhLuanDTO> mapToBLDTO(String isbn) {
        List<Map<String,Object>> data = sachRepository.layDanhSachBinhLuanCuaSach(isbn);
        return data.stream().map(map ->
                BinhLuanDTO.builder()
                        .idBinhLuan((Integer) map.get("IDBINHLUAN"))
                        .tenDangNhap((String) map.get("TENDANGNHAP"))
                        .isbn((String) map.get("ISBN"))
                        .noiDung((String) map.get("NOIDUNG"))
                        .diem((Integer) map.get("DIEM"))
                        .ngay((Date) map.get("NGAY"))
                        .hoTen((String) map.get("HOTEN"))
                        .hinhAnh((String) map.get("HINHANH"))
                        .build()
                ).collect(Collectors.toList());
    }

    private List<TacGia> mapObToTG(String isbn) {
        List<Map<String, Object>> data = sachRepository.layDanhSachTacGiaSach(isbn);
        return data.stream().map(map ->
                TacGia.builder()
                        .idTacGia((Integer) map.get("IDTACGIA"))
                        .ho((String) map.get("HO"))
                        .ten((String) map.get("TEN"))
                        .build()
        ).collect(Collectors.toList());
    }

    private List<TheLoai> mapObToTL(String isbn) {
        List<Map<String, Object>> data = sachRepository.layDanhSachTheLoaiSach(isbn);
        return data.stream().map(map ->
                TheLoai.builder()
                        .idTheLoai((Integer) map.get("IDTHELOAI"))
                        .tenTheLoai((String) map.get("TENTHELOAI"))
                        .build()
        ).collect(Collectors.toList());
    }

    private List<HinhAnh> mapObToHA(String isbn) {
        List<Map<String, Object>> data = sachRepository.layDanhSachHinhAnhSach(isbn);
        return data.stream().map(map ->
                HinhAnh.builder()
                        .idAnh((Integer) map.get("IDANH"))
                        .isbn((String) map.get("ISBN"))
                        .filename((String) map.get("FILENAME"))
                        .build()
        ).collect(Collectors.toList());
    }
}
