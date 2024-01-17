package com.johyeyeong.parking.apply.repository;

import com.johyeyeong.parking.apply.entity.CarQtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarQtRtRepository extends JpaRepository<CarQtEntity, String> {
    List<CarQtEntity> findAll();
}
