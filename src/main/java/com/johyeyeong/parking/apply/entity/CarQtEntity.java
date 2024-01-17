package com.johyeyeong.parking.apply.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_car_qtt_rts")
public class CarQtEntity {

    @Id
    @Column(name = "qt")
    private int qt;

    @Column(name = "rt")
    private long rt;
}
