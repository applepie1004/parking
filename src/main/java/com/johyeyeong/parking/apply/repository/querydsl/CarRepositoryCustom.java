package com.johyeyeong.parking.apply.repository.querydsl;

import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.entity.CarKeyEntity;
import com.querydsl.core.Tuple;


import java.util.List;
import java.util.UUID;

public interface CarRepositoryCustom {
    CarEntity findByAptIdAndCarNum(UUID aptId, String carNum);
}
