package com.johyeyeong.parking.adm.entity;

import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.entity.CarKeyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@IdClass(CarKeyEntity.class)
@NoArgsConstructor
public class CarAndApartmentEntity {

    public CarAndApartmentEntity(ApartmentEntity apartment, CarEntity car) {
        this.aptId = apartment.getAptId();
        this.carNum = car.getCarNum();
        this.status = car.getStatus();
        this.carRegDt = car.getRegDt();
        this.dong = apartment.getDong();
        this.ho = apartment.getHo();
        this.owner = apartment.getOwner();
        this.phone = apartment.getPhone();
    }


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

    @Column(name = "owner")
    private String owner;

    @Column(name = "phone")
    private String phone;

}
