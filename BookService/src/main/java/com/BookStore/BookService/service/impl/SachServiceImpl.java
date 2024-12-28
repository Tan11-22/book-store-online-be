package com.BookStore.BookService.service.impl;

import com.BookStore.BookService.dto.*;
import com.BookStore.BookService.model.HinhAnh;
import com.BookStore.BookService.model.Sach;
import com.BookStore.BookService.model.TacGia;
import com.BookStore.BookService.model.TheLoai;
import com.BookStore.BookService.repository.SachRepository;
import com.BookStore.BookService.service.SachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

@Service
public class SachServiceImpl implements SachService {
    @Autowired
    private SachRepository sachRepository;
    @Autowired
    private DataSource dataSource;
    Logger logger = LoggerFactory.getLogger(SachServiceImpl.class.getName());

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
                .soLuong((Integer) data.get("SOLUONG"))
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
        if (data.size() == 0) {
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

    @Override
    public BookStoreResponse demSLSachTimRa(String search) {
        return BookStoreResponse.<Integer>builder()
                .code(200)
                .status("Lấy tổng số lượng thành công!")
                .data(sachRepository.demSachTimRa(search)).build();
    }

    @Override
    public BookStoreResponse layDSSachBanChay(int start, int size) {
        List<Map<String, Object>> data = sachRepository.layDSSachBanChay(start, size);
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
//        List<BinhLuanDTO> binhLuanDTOS = mapToBLDTO(isbn);

        return ChiTietSachDTO.builder()
                .sachDTO(sachDTO)
                .hinhAnhs(hinhAnhs)
                .tacGias(tacGias)
                .theLoais(theLoais)
//                .binhLuanDTOS(binhLuanDTOS)
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

//    private List<BinhLuanDTO> mapToBLDTO(String isbn) {
//        List<Map<String,Object>> data = sachRepository.layDanhSachBinhLuanCuaSach(isbn);
//        return data.stream().map(map ->
//                BinhLuanDTO.builder()
//                        .idBinhLuan((Integer) map.get("IDBINHLUAN"))
//                        .tenDangNhap((String) map.get("TENDANGNHAP"))
//                        .isbn((String) map.get("ISBN"))
//                        .noiDung((String) map.get("NOIDUNG"))
//                        .diem((Integer) map.get("DIEM"))
//                        .ngay((Date) map.get("NGAY"))
//                        .hoTen((String) map.get("HOTEN"))
//                        .hinhAnh((String) map.get("HINHANH"))
//                        .build()
//                ).collect(Collectors.toList());
//    }

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


    private List<CardSach> timSachNangCao(String query,
                                          List<String> tacGiaIDs,
                                          List<String> theLoaiIDs,
                                          int sapXep,
                                          int start,
                                          int size
    ) {
        List<CardSach> books = new ArrayList<>();
        String find = "SELECT * FROM SACH WHERE";
        String find1 = " ISBN LIKE N'%" + query + "%' OR TENSACH LIKE N'%" + query + "%' OR MOTA LIKE N'%" + query + "%' ";
        String find2 = "";
        String find3 = "";
        if (tacGiaIDs.size() == 0||tacGiaIDs.get(0).equals("")) {
            find2 +=
                    " ISBN IN " +
                            "(SELECT ISBN FROM (SELECT * FROM TACGIA WHERE HO + ' ' + TEN LIKE N'%" + query + "%')" +
                            " TG INNER JOIN (SELECT * FROM SANGTAC) ST ON ST.IDTACGIA = TG. IDTACGIA)"
            ;
        } else {
            find2 +=
                    " ISBN IN (SELECT ISBN FROM SANGTAC WHERE IDTACGIA=" + tacGiaIDs.get(0);
            for (int i = 1; i < tacGiaIDs.size(); i++) {
                find2 += " OR IDTACGIA=" + tacGiaIDs.get(i);
            }
            find2 += " GROUP BY ISBN HAVING COUNT(DISTINCT IDTACGIA) >=" + tacGiaIDs.size() + ") ";

        }


        if (theLoaiIDs.size() == 0||theLoaiIDs.get(0).equals("")) {
            find3 +=
                    " ISBN IN " +
                            "(SELECT ISBN FROM (SELECT * FROM THELOAI WHERE TENTHELOAI LIKE N'%" + query + "%')" +
                            " TL INNER JOIN (SELECT * FROM THELOAISACH) TLS ON TL.IDTHELOAI = TLS.IDTHELOAI)"
            ;
        } else {
            find3 +=
                    " ISBN IN (SELECT ISBN FROM THELOAISACH WHERE IDTHELOAI=" + theLoaiIDs.get(0);
            for (int i = 1; i < theLoaiIDs.size(); i++) {
                find3 += " AND IDTHELOAI=" + theLoaiIDs.get(i);
            }
            find3 += " GROUP BY ISBN HAVING COUNT(DISTINCT IDTHELOAI) >=" + theLoaiIDs.size() + ") ";

        }

        if (!query.equals("")) {
            find += find1 + " OR " + find2 + " OR " + find3;
        } else {
            find += (tacGiaIDs.size() != 0 && theLoaiIDs.size() != 0) ? find2 + " OR " + find3
                    : (tacGiaIDs.size() != 0 ? find2 : find3);
        }
//        logger.error("check log offset:");
//        logger.error(String.valueOf(start));
//        logger.error(String.valueOf(size));
        String sql = "DECLARE @ngayhientai DATE ; SET @ngayhientai = CAST(GETDATE() AS DATE);" +
                "SELECT S.ISBN, S.TENSACH, TENTACGIA = TG.HO + ' ' + TG.TEN, " +
                " CTGS.GIA AS GIABAN, CTGS1.GIA AS GIAGIAM, " +
                " HA.FILENAME AS TENANH, S.SOLUONG  FROM (" +
                find +   // câu lệnh query tìm kiếm
                " ) S INNER JOIN ( SELECT * FROM CTGIASACH WHERE IDGIA = '2' AND @ngayhientai BETWEEN NGAYAPDUNG AND NGAYKETTHUC" +
                ") CTGS " +
                " ON CTGS.ISBN = S.ISBN" +
                " LEFT JOIN ( SELECT * FROM CTGIASACH WHERE IDGIA = '3' AND @ngayhientai BETWEEN NGAYAPDUNG AND NGAYKETTHUC" +
                " ) CTGS1 ON CTGS1.ISBN = S.ISBN LEFT JOIN (" +
                "SELECT * FROM HINHANH WHERE IDANH IN ( SELECT MIN(IDANH) FROM HINHANH GROUP BY ISBN)" +
                ") HA ON HA.ISBN = S.ISBN LEFT JOIN ( SELECT *" +
                " FROM SANGTAC WHERE IDSANGTAC IN (SELECT MIN(IDSANGTAC) FROM SANGTAC GROUP BY ISBN)" +
                ") ST ON ST.ISBN = S.ISBN INNER JOIN (" +
                "SELECT * FROM TACGIA) TG ON TG.IDTACGIA = ST.IDTACGIA"
                +" ORDER BY GIABAN " + (sapXep == 0? "ASC":"DESC") // ASC tăng dần , desc giảm dần
                + " OFFSET ("+String.valueOf(start)+") ROWS FETCH NEXT ("+String.valueOf(size)+") ROWS ONLY;"
                ;


        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CardSach book = new CardSach();
                book.setIsbn(rs.getString("ISBN"));
                book.setTenSach(rs.getString("TENSACH"));
                book.setTenTacGia(rs.getString("TENTACGIA"));
                book.setGiaBan(rs.getInt("GIABAN"));
                book.setGiaGiam(rs.getInt("GIAGIAM"));
                book.setTenAnh(rs.getString("TENANH"));
                book.setSoLuong(rs.getInt("SOLUONG"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    private Integer demSoLuongSachTimNangCao(String query,
                                             List<String> tacGiaIDs,
                                             List<String> theLoaiIDs) {
//        List<CardSach> books = new ArrayList<>();
//        System.out.println(tacGiaIDs);
//        System.out.println(tacGiaIDs.get(0));
//        System.out.println(theLoaiIDs);
        Integer result = 0;
        String find = "SELECT * FROM SACH WHERE";
        String find1 = " ISBN LIKE N'%" + query + "%' OR TENSACH LIKE N'%" + query + "%' OR MOTA LIKE N'%" + query + "%' ";
        String find2 = "";
        String find3 = "";
        if (tacGiaIDs.size() == 0||tacGiaIDs.get(0).equals("")) {
            find2 +=
                    " ISBN IN " +
                            "(SELECT ISBN FROM (SELECT * FROM TACGIA WHERE HO + ' ' + TEN LIKE N'%" + query + "%')" +
                            " TG INNER JOIN (SELECT * FROM SANGTAC) ST ON ST.IDTACGIA = TG. IDTACGIA)"
            ;
        } else {
            find2 +=
                    " ISBN IN (SELECT ISBN FROM SANGTAC WHERE IDTACGIA=" + tacGiaIDs.get(0);
            for (int i = 1; i < tacGiaIDs.size(); i++) {
                find2 += " OR IDTACGIA=" + tacGiaIDs.get(i);
            }
            find2 += " GROUP BY ISBN HAVING COUNT(DISTINCT IDTACGIA) >=" + tacGiaIDs.size() + ") ";

        }


        if (theLoaiIDs.size() == 0||theLoaiIDs.get(0).equals("")) {
            find3 +=
                    " ISBN IN " +
                            "(SELECT ISBN FROM (SELECT * FROM THELOAI WHERE TENTHELOAI LIKE N'%" + query + "%')" +
                            " TL INNER JOIN (SELECT * FROM THELOAISACH) TLS ON TL.IDTHELOAI = TLS.IDTHELOAI)"
            ;
        } else {
            find3 +=
                    " ISBN IN (SELECT ISBN FROM THELOAISACH WHERE IDTHELOAI=" + theLoaiIDs.get(0);
            for (int i = 1; i < theLoaiIDs.size(); i++) {
                find3 += " AND IDTHELOAI=" + theLoaiIDs.get(i);
            }
            find3 += " GROUP BY ISBN HAVING COUNT(DISTINCT IDTHELOAI) >=" + theLoaiIDs.size() + ") ";

        }

        if (!query.equals("")) {
            find += find1 + " OR " + find2 + " OR " + find3;
        } else {
            find += (tacGiaIDs.size() != 0 && theLoaiIDs.size() != 0) ? find2 + " OR " + find3
                    : (tacGiaIDs.size() != 0 ? find2 : find3);
        }
        String sql = "DECLARE @ngayhientai DATE ; SET @ngayhientai = CAST(GETDATE() AS DATE);" +
                "SELECT COUNT(S.ISBN)  FROM (" +
                find +   // câu lệnh query tìm kiếm
                " ) S INNER JOIN ( SELECT * FROM CTGIASACH WHERE IDGIA = '2' AND @ngayhientai BETWEEN NGAYAPDUNG AND NGAYKETTHUC" +
                ") CTGS " +
                " ON CTGS.ISBN = S.ISBN "+
                "INNER JOIN (SELECT * FROM SANGTAC WHERE IDSANGTAC IN ( SELECT MIN(IDSANGTAC)FROM SANGTAC GROUP BY ISBN)) ST ON ST.ISBN = S.ISBN"
                ;
//        System.out.println(sql);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if(rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public BookStoreResponse<List<CardSach>> timSach1(Map<String, Object> data) {
        String search = (String)data.get("search");
        List<String> tacGiaIDs= (List<String>) data.get("tacGiaIDs");
        List<String> theLoaiIDs =  (List<String>) data.get("theLoaiIDs");
        Integer sapXep = (Integer) data.get("sapXep");
        Integer start = (Integer) data.get("start");
        Integer size = (Integer) data.get("size");
        List<CardSach> books = timSachNangCao(search, tacGiaIDs, theLoaiIDs,sapXep, start, size);
        return BookStoreResponse.<List<CardSach>>builder()
                .code(200)
                .status("")
                .data(books).build();
    }

    public BookStoreResponse<Integer> countTimSach1(Map<String, Object> data) {
        String search = (String)data.get("search");
        List<String> tacGiaIDs= (List<String>) data.get("tacGiaIDs");
        List<String> theLoaiIDs =  (List<String>) data.get("theLoaiIDs");
        Integer count = demSoLuongSachTimNangCao(search, tacGiaIDs, theLoaiIDs);
        return BookStoreResponse.<Integer>builder()
                .code(200)
                .status("")
                .data(count).build();
    }


    private TacGiaDTO mapDataToTG(Map<String, Object> data) {
        return TacGiaDTO.builder()
                .idTacGia((Integer) data.get("IDTACGIA"))
                .hoTen((String) data.get("HO")+" "+ (String) data.get("TEN"))
                .build();
    }



    private TheLoai mapDataToTL(Map<String, Object> data) {
        return TheLoai.builder()
                .idTheLoai((Integer) data.get("IDTHELOAI"))
                .tenTheLoai((String) data.get("TENTHELOAI"))
                .build();
    }

    @Override
    public BookStoreResponse<List<TacGiaDTO>> layTCTG() {
        List<TacGiaDTO> result = sachRepository.layTCTG().stream().map(map -> mapDataToTG(map)).toList();
        return BookStoreResponse.<List<TacGiaDTO>>builder()
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

    private List<CardSach> getSachTuongTu(String isbn) {
        List<CardSach> books = new ArrayList<>();
        List<Integer> idTheLoais = sachRepository.getIdTheLoaiTT(isbn);
        System.out.println(idTheLoais);
        String find = "SELECT * FROM SACH WHERE";
//        String find1 = " ISBN LIKE '%" + query + "%' OR TENSACH LIKE '%" + query + "%'";
        find += " ISBN IN (SELECT ISBN FROM THELOAISACH WHERE ISBN !='"+isbn+"' AND IDTHELOAI=" + idTheLoais.get(0);
            for (int i = 1; i < idTheLoais.size(); i++) {
                find += " OR IDTHELOAI=" + idTheLoais.get(i);
            }
        find += ") ";
//        logger.error(find);
        String sql = "DECLARE @ngayhientai DATE ; SET @ngayhientai = CAST(GETDATE() AS DATE);" +
                " SELECT TOP 3 S.ISBN, S.TENSACH, TENTACGIA = TG.HO + ' ' + TG.TEN, " +
                " CTGS.GIA AS GIABAN, CTGS1.GIA AS GIAGIAM, " +
                " HA.FILENAME AS TENANH  FROM (" +
                find +   // câu lệnh query tìm kiếm
                " ) S INNER JOIN ( SELECT * FROM CTGIASACH WHERE IDGIA = '2' AND @ngayhientai BETWEEN NGAYAPDUNG AND NGAYKETTHUC" +
                ") CTGS " +
                " ON CTGS.ISBN = S.ISBN" +
                " LEFT JOIN ( SELECT * FROM CTGIASACH WHERE IDGIA = '3' AND @ngayhientai BETWEEN NGAYAPDUNG AND NGAYKETTHUC" +
                " ) CTGS1 ON CTGS1.ISBN = S.ISBN LEFT JOIN (" +
                "SELECT * FROM HINHANH WHERE IDANH IN ( SELECT MIN(IDANH) FROM HINHANH GROUP BY ISBN)" +
                ") HA ON HA.ISBN = S.ISBN LEFT JOIN ( SELECT *" +
                " FROM SANGTAC WHERE IDSANGTAC IN (SELECT MIN(IDSANGTAC) FROM SANGTAC GROUP BY ISBN)" +
                ") ST ON ST.ISBN = S.ISBN INNER JOIN (" +
                "SELECT * FROM TACGIA) TG ON TG.IDTACGIA = ST.IDTACGIA"
               + " ORDER BY GIABAN ASC"
                ;
//        logger.info(sql);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CardSach book = new CardSach();
                book.setIsbn(rs.getString("ISBN"));
                book.setTenSach(rs.getString("TENSACH"));
                book.setTenTacGia(rs.getString("TENTACGIA"));
                book.setGiaBan(rs.getInt("GIABAN"));
                book.setGiaGiam(rs.getInt("GIAGIAM"));
                book.setTenAnh(rs.getString("TENANH"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    @Override
    public BookStoreResponse<List<CardSach>> getSachCungTheLoai(String isbn) {
        List<CardSach> books = getSachTuongTu(isbn);
        return BookStoreResponse.<List<CardSach>>builder()
                .code(200)
                .status("Lấy danh sách sách cùng thể loại thành công!")
                .data(books).build();
    }
}
