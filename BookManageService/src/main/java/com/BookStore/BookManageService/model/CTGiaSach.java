package com.BookStore.BookManageService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CTGIASACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTGiaSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTGIASACH")
    private Integer idCTGiaSach;
    @Column(name = "IDGIA")
    private Integer idGia;
    @Column(name = "GIA")
    private Integer gia;
    @Column(name = "NGAYAPDUNG")
    private String ngayApDung;
    @Column(name = "NGAYKETTHUC")
    private String ngayKetThuc;
    @Column(name = "ISBN")
    private String isbn;
}
