package com.johyeyeong.parking.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="tb_apt")
public class ApartmentEntity {

    @Id
    @Column(name = "apt_id")
    private String aptId;

    @Column(name = "dong")
    private int dong;

    @Column(name = "ho")
    private int ho;

    @Column(name = "onwer")
    private String onwer;

    @Column(name = "phone")
    private String phone;

    @Column(name = "remark")
    private String remark;

    @Column(name = "regDt")
    private Timestamp regDt;


}
