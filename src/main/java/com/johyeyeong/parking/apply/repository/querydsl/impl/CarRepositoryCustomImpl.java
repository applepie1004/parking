package com.johyeyeong.parking.apply.repository.querydsl.impl;

import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.entity.QApartmentEntity;
import com.johyeyeong.parking.apply.entity.QCarEntity;
import com.johyeyeong.parking.apply.repository.querydsl.CarRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CarEntity findByAptIdAndCarNum(UUID aptId, String carNum) {
        QCarEntity QCar  = QCarEntity.carEntity;
        return jpaQueryFactory
                .select(QCar)
                .from(QCar)
                .where(QCar.aptId.eq(aptId))
                .where(QCar.carNum.eq(carNum))
                .fetchOne();
    }

}
