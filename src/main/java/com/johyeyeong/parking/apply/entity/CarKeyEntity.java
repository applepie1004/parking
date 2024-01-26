package com.johyeyeong.parking.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarKeyEntity implements Serializable {

    @Column(name = "apt_id")
    private UUID aptId;

    @Column(name = "car_num")
    private String carNum;
}
