package com.johyeyeong.parking.apply.repository.querydsl.impl;

import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.entity.CarKeyEntity;
import com.johyeyeong.parking.apply.entity.QApartmentEntity;
import com.johyeyeong.parking.apply.entity.QCarEntity;
import com.johyeyeong.parking.apply.repository.querydsl.CarRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;

import java.util.List;
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

    @Override
    public List<Tuple> findCarAndApartment() {
        List<Tuple> list = null;
        QCarEntity qCar = QCarEntity.carEntity;
        QApartmentEntity qApartment = QApartmentEntity.apartmentEntity;

        list = jpaQueryFactory.select(qCar, qApartment)
                .from(qCar)
                .join(qApartment)
                .on(qApartment.aptId.eq(qCar.aptId))
                .fetch();

        return list;
    }
}
