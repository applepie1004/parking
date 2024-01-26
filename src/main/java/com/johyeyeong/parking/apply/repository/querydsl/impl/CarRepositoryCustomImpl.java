package com.johyeyeong.parking.apply.repository.querydsl.impl;

import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.entity.CarKeyEntity;
import com.johyeyeong.parking.apply.entity.QCarEntity;
import com.johyeyeong.parking.apply.repository.querydsl.CarRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

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
