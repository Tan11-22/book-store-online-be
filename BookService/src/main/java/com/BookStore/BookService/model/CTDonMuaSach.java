package com.BookStore.BookService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "CTDONMUASACH")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTDonMuaSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCTDONMUASACH")
    private Integer idCTDonMuaSach;
    @Column(name = "IDDONMUASACH")
    private Integer idDonMuaSach;
    @Column(name="ISBN")
    private String isbn;
    @Column(name = "SOLUONG")
    private Integer soLuong;
    @Column(name = "DONGIA")
    private Integer donGia;

}
