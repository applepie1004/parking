package com.johyeyeong.parking.adm.repository;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.adm.entity.CarAndApartmentEntity;
import com.johyeyeong.parking.adm.repository.querydsl.CarAndApartmentCustom;
import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import com.johyeyeong.parking.apply.entity.CarEntity;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarAndApartmentRepository extends JpaRepository<CarEntity, String>, CarAndApartmentCustom {

    @Override
    List<CarAndApartmentEntity> findCarAndApartment(StatusParam param);
}
