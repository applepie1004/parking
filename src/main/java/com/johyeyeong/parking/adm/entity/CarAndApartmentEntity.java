package com.johyeyeong.parking.adm.entity;

import com.johyeyeong.parking.apply.entity.CarKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@IdClass(CarKeyEntity.class)
public class CarAndApartmentEntity {
    @Id
    @Column(name = "apt_id")
    private UUID aptId;

    @Id
    @Column(name = "car_num")
    private String carNum;


    @Column(name = "status")
    private String status;

    @Column(name = "reg_dt")
    private Timestamp carRegDt;

    @Column(name = "dong")
    private int dong;

    @Column(name = "ho")
    private int ho;

    @Column(name = "onwer")
    private String onwer;

    @Column(name = "phone")
    private String phone;

}
