package com.johyeyeong.parking.apply.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="tb_car")
public class CarEntity {

    @Id
    @Column(name = "apt_id")
    private UUID aptId;

    @Column(name = "car_num")
    private String carNum;

    @Column(name = "status")
    private String status;
}
