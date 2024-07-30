package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.BookStoreResponse;
import com.BookStore.BookService.dto.GioHangDTO;
import com.BookStore.BookService.repository.GioHangRepository;
import com.BookStore.BookService.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.management.ObjectName;
import java.util.List;
import java.util.Map;

@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Override
    public BookStoreResponse<List<GioHangDTO>> layChiTietGioHang(String tenDangNhap) {
        List<Map<String, Object>> data = gioHangRepository.layChiTietGioHang(tenDangNhap);
        List<GioHangDTO> result = data.stream().map(map -> mapGHToDTO(map)).toList();
        return BookStoreResponse.<List<GioHangDTO>>builder()
                .code(200)
                .status("Lấy chi tiết giỏ hàng thành công!")
                .data(result)
                .build();
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> themSachVaoGioHang(String ten, String isbn, int soLuong) {
        Map<String, Object> dataGH = gioHangRepository.layIdGioHang(ten,isbn);
        Integer id = (Integer) dataGH.get("IDGIOHANG");
        System.out.println(id);
//        System.out.println()
        if(id != null) return capNhatSachTrongGioHang(id, soLuong + (Integer) dataGH.get("SOLUONG"));
        try {
            gioHangRepository.themSachVaoGioHang(ten, isbn, soLuong);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Thêm sách thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Them sach vao gio hang--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Thêm sách thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> capNhatSachTrongGioHang(int id, int soLuong) {
        try {
            gioHangRepository.capNhatSachTrongGioHang(id, soLuong);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Cập nhật số lượng sách thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Cap nhat sach vao gio hang--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Cập nhật số lượng sách thất bại!")
                    .data(false)
                    .build();
        }
    }

    @Override
    @Transactional
    public BookStoreResponse<Boolean> xoaSachTrongGioHang(int id) {
        try {
            gioHangRepository.xoaSachTrongGioHang(id);
            return BookStoreResponse.<Boolean>builder()
                    .code(200)
                    .status("Xoá sách thành công!")
                    .data(true)
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Xoá sach trong gio hang--- " + e.getMessage());
            return BookStoreResponse.<Boolean>builder()
                    .code(201)
                    .status("Xoá sách thất bại!")
                    .data(false)
                    .build();
        }
    }



    @Override
    public BookStoreResponse<Integer> laySLSachTrongGH(String tenDangNhap) {
        Integer result = gioHangRepository.laySLSachTrongGH(tenDangNhap);
        return BookStoreResponse.<Integer>builder()
                .code(200)
                .status("Lấy số lượng sách trong giỏ hàng thành công!")
                .data(result)
                .build();
    }

    private GioHangDTO mapGHToDTO(Map<String, Object> data) {
        return GioHangDTO.builder()
                .idGioHang((Integer) data.get("IDGIOHANG"))
                .tenSach((String) data.get("TENSACH"))
                .soLuong((Integer) data.get("SOLUONG"))
                .giaBan((Integer) data.get("GIABAN"))
                .giaGiam((Integer) data.get("GIAGIAM"))
                .anh((String) data.get("TENANH"))
                .selected(false)
                .build();
    }
}
