package com.BookStore.BookManageService.service.impl;

import com.BookStore.BookManageService.dto.*;
import com.BookStore.BookManageService.model.HinhAnh;
import com.BookStore.BookManageService.model.NhaXuatBan;
import com.BookStore.BookManageService.model.TacGia;
import com.BookStore.BookManageService.model.TheLoai;
import com.BookStore.BookManageService.repository.SachRepository;
import com.BookStore.BookManageService.service.FileService;
import com.BookStore.BookManageService.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SachServiceImpl implements SachService {
    @Autowired
    private SachRepository sachRepository;

    @Autowired
    private FileService fileService;

    @Override
    public BookStoreResponse<List<SachDTO>> layDSSachQT(String search) {
        List<Map<String, Object>> data = sachRepository.layDSSachQT(search);
        List<SachDTO> result = data.stream().map(map -> mapDataToDTO(map)).toList();
        return BookStoreResponse.<List<SachDTO>>builder()
                .code(200)
                .status("Lấy danh sách sách thành công!")
                .data(result)
                .build();
    }

    @Override
    @Transactional
    public BookStoreResponse<String> themSachMoi(String isbn, String tenSach, Integer soTrang, String khuonKho, Integer trongLuong,
                                                 String moTa, String nxb, List<String> chipTGs, List<String> chipTLs, List<MultipartFile> files) {
        if(sachRepository.checkISBN(isbn) == 1)
            return BookStoreResponse.<String>builder()
                    .code(201)
                    .status("Mã ISBN của sách đã tồn tại!")
                    .data("")
                    .build();

        try {
            sachRepository.themSach(isbn,tenSach,khuonKho,soTrang,trongLuong,moTa,nxb);
            chipTGs.stream().forEach(map -> insertST(map,isbn));
            chipTLs.stream().forEach(map->insertTLS(map,isbn));
            for(int i=0;i< files.size();i++) {
                String fileName = isbn+"a"+String.valueOf(i+1)+".png";
                fileService.saveFile(files.get(i),0,fileName);
                sachRepository.themHinhAnh(isbn,fileName);
            }
            return BookStoreResponse.<String>builder()
                    .code(200)
                    .status("Thêm sách thành công!")
                    .data("")
                    .build();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("ERROR-62: Cap nhat sach vao gio hang--- " + e.getMessage());
            return BookStoreResponse.<String>builder()
                    .code(202)
                    .status("Đã có lỗi xảy ra!")
                    .data("")
                    .build();
        }

    }

    private void insertTLS(String data, String isbn) {
        Integer idTL = Integer.parseInt(data.split("-")[0].trim().toString());
        System.out.println("check" + idTL);
        sachRepository.themTheLoaiSach(isbn,idTL);
    }

    private void insertST(String data, String isbn) {
        Integer idTG = Integer.parseInt(data.split("-")[0].trim().toString());
        System.out.println("check" + idTG);
        sachRepository.themSangTac(isbn,idTG);
    }

    @Override
    public BookStoreResponse<List<TacGia>> layTCTG() {
        List<TacGia> result = sachRepository.layTCTG().stream().map(map -> mapDataToTG(map)).toList();
        return BookStoreResponse.<List<TacGia>>builder()
                .code(200)
                .status("Lấy danh sách tác giả thành công!")
                .data(result)
                .build();
    }

    @Override
    public BookStoreResponse<List<TheLoai>> layTCTL() {
        List<TheLoai> result = sachRepository.layTCTL().stream().map(map -> mapDataToTL(map)).toList();
        return BookStoreResponse.<List<TheLoai>>builder()
                .code(200)
                .status("Lấy danh sách thể loại thành công!")
                .data(result)
                .build();
    }

    @Override
    public BookStoreResponse<List<NhaXuatBan>> layTCNXB() {
        List<NhaXuatBan> result = sachRepository.layTCNXB().stream().map(map -> mapDataToNXB(map)).toList();
        return BookStoreResponse.<List<NhaXuatBan>>builder()
                .code(200)
                .status("Lấy danh sách nhà xuất bản thành công!")
                .data(result)
                .build();
    }

    @Override
    public BookStoreResponse thongKeDoanhThuNam(int year) {
        List<DoanhThuDTO> doanhThuDTOS = new ArrayList<>();
        for(int i=0; i <12; i++) {
            DoanhThuDTO doanhThuDTO = DoanhThuDTO.builder()
                    .thang(i+1)
                    .nam(year)
                    .thanhTienBan(0L)
                    .thanhTienNhap(0L)
                    .tongSoSachBan(0L)
                    .tongSoSachNhap(0L)
                    .build();
            doanhThuDTOS.add(doanhThuDTO);
        }
        List<Map<String, Object>> dataQuery = sachRepository.thongKeDoanhThuNam(year);
        for(Map<String, Object> data : dataQuery) {
            int thang = (Integer) data.get("THANG");
            int nam = (Integer) data.get("NAM");
            long tongSo = (Long) data.get("TONGSO");
            long thanhTien = (Long) data.get("THANHTIEN");
            int kieu = (Integer) data.get("KIEU");
            if(kieu == 1) {
                long tienTMP =  doanhThuDTOS.get(thang-1).getThanhTienBan();
                long sachTMP =  doanhThuDTOS.get(thang-1).getTongSoSachBan();
                doanhThuDTOS.get(thang-1).setThanhTienBan(tienTMP+thanhTien);
                doanhThuDTOS.get(thang-1).setTongSoSachBan(sachTMP+tongSo);
            } else {
                long tienTMP =  doanhThuDTOS.get(thang-1).getThanhTienNhap();
                long sachTMP =  doanhThuDTOS.get(thang-1).getTongSoSachNhap();
                doanhThuDTOS.get(thang-1).setThanhTienNhap(tienTMP+thanhTien);
                doanhThuDTOS.get(thang-1).setTongSoSachNhap(sachTMP+tongSo);
            }
        }
        return BookStoreResponse.<List<DoanhThuDTO>>builder()
                .code(200)
                .status("Lấy doanh thu thành công!")
                .data(doanhThuDTOS).build();
    }




    private SachDTO mapDataToDTO(Map<String, Object> data) {
        List<TacGia> tacGias = sachRepository.layDSTGQT((String) data.get("ISBN")).stream().map(map->mapDataToTG(map)).toList();
        List<TheLoai> theLoais = sachRepository.layDSTLQT((String) data.get("ISBN")).stream().map(map->mapDataToTL(map)).toList();
        List<HinhAnh> hinhAnhs = sachRepository.layDSHAQT((String) data.get("ISBN")).stream().map(map->mapDataToHA(map)).toList();
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
                .tacGias(tacGias)
                .hinhAnhs(hinhAnhs)
                .theLoais(theLoais)
                .build();
    }

    private TacGia mapDataToTG(Map<String, Object> data) {
        return TacGia.builder()
                .idTacGia((Integer) data.get("IDTACGIA"))
                .ho((String) data.get("HO"))
                .ten((String) data.get("TEN"))
                .build();
    }

    private NhaXuatBan mapDataToNXB(Map<String, Object> data) {
        return NhaXuatBan.builder()
                .maNhaXuatBan((String) data.get("MANHAXUATBAN"))
                .tenNhaXuatBan((String) data.get("TENNHAXUATBAN"))
                .chuSoHuu((String) data.get("CHUSOHUU"))
                .build();
    }

    private TheLoai mapDataToTL(Map<String, Object> data) {
        return TheLoai.builder()
                .idTheLoai((Integer) data.get("IDTHELOAI"))
                .tenTheLoai((String) data.get("TENTHELOAI"))
                .build();
    }

    private HinhAnh mapDataToHA(Map<String, Object> data) {
        return HinhAnh.builder()
                .idAnh((Integer) data.get("IDANH"))
                .isbn((String) data.get("ISBN"))
                .filename((String) data.get("FILENAME"))
                .build();
    }
}
