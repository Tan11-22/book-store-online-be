package com.BookStore.BookService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "NHAXUATBAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NhaXuatBan {
    @Id
    @Column(name = "MANHAXUATBAN")
    private String maNhaXuatBan;

    @Column(name="TENNHAXUATBAN")
    private String tenNhaXuatBan;

    @Column(name = "CHUSOHUU")
    private String chuSoHuu;

}
