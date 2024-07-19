package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.BinhLuanDTO;
import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.ChiTietSachDTO;
import com.BookStore.BookService.dto.SachDTO;
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

    @Override
    public List<Sach> layDSSach(int start, int size) {
        List<Sach> sachs = sachRepository.layDSSach(start, size);
        return sachs;
    }

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

    private ChiTietSachDTO taoDoiTuongChiTietSach(String isbn) {
        SachDTO sachDTO = mapToDTO(sachRepository.layChiTietSach(isbn));
        List<HinhAnh> hinhAnhs = sachRepository.layDanhSachHinhAnhSach(isbn);
        List<TacGia> tacGias = sachRepository.layDanhSachTacGiaSach(isbn);
        List<TheLoai> theLoais = sachRepository.layDanhSachTheLoaiSach(isbn);
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
}
