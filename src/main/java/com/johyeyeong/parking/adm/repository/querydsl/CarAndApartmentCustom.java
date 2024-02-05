package com.johyeyeong.parking.adm.repository.querydsl;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.adm.entity.CarAndApartmentEntity;
import com.querydsl.core.Tuple;

import java.util.List;

public interface CarAndApartmentCustom {

    List<CarAndApartmentEntity> findCarAndApartment(StatusParam param);
}
