package com.BookStore.BookManageService.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "NHAXUATBAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NhaXuatBan {
    @Id
    @Column(name = "MANHAXUATBAN")
    private String maNhaXuatBan;

    @Column(name="TENNHAXUATBAN")
    private String tenNhaXuatBan;

    @Column(name = "CHUSOHUU")
    private String chuSoHuu;

}
