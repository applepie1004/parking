package com.johyeyeong.parking.adm.repository.querydsl.Impl;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.adm.entity.CarAndApartmentEntity;
import com.johyeyeong.parking.adm.repository.querydsl.CarAndApartmentCustom;
import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import com.johyeyeong.parking.apply.entity.QApartmentEntity;
import com.johyeyeong.parking.apply.entity.QCarEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class CarAndApartmentCustomImpl implements CarAndApartmentCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CarAndApartmentEntity> findCarAndApartment(StatusParam param) {
        List<CarAndApartmentEntity> list2 = new ArrayList<>();
        QCarEntity qCar = QCarEntity.carEntity;
        QApartmentEntity qApartment = QApartmentEntity.apartmentEntity;

        BooleanBuilder builder = new BooleanBuilder();

        if (!StringUtils.isEmpty(param.getStatus())) {
            builder.and(qCar.status.eq(param.getStatus()));
        }
        if (!StringUtils.isEmpty(param.getCarNum())) {
            builder.and(qCar.carNum.eq(param.getCarNum()));
        }
        if (param.getDong() != null) {
            builder.and(qApartment.dong.eq(param.getDong()));
        }
        if (param.getHo() != null) {
            builder.and(qApartment.ho.eq(param.getHo()));
        }

        List<Tuple> list = jpaQueryFactory.select(qCar, qApartment)
                .from(qCar)
                .join(qApartment)
                .on(qApartment.aptId.eq(qCar.aptId))
                .where(builder)
                .fetch();

        for(Tuple tuple : list) {
            CarAndApartmentEntity entity = new CarAndApartmentEntity(tuple.get(qApartment), tuple.get(qCar));
            list2.add(entity);
        }

        return list2;
    }

}
