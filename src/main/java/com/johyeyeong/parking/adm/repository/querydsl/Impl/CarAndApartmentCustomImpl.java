package com.johyeyeong.parking.adm.repository.querydsl.Impl;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.adm.entity.CarAndApartmentEntity;
import com.johyeyeong.parking.adm.repository.querydsl.CarAndApartmentCustom;
import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import com.johyeyeong.parking.apply.entity.QApartmentEntity;
import com.johyeyeong.parking.apply.entity.QCarEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
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

    QCarEntity qCar = QCarEntity.carEntity;
    QApartmentEntity qApartment = QApartmentEntity.apartmentEntity;

    @Override
    public List<CarAndApartmentEntity> findCarAndApartment(StatusParam param) {
        List<CarAndApartmentEntity> list2 = new ArrayList<>();

        List<Tuple> list =
                jpaQueryFactory.select(qCar, qApartment)
                .from(qCar)
                .join(qApartment)
                .on(qApartment.aptId.eq(qCar.aptId))
                .where(eqStatus(param.getStatus()),
                        eqDong(param.getDong()),
                        eqHo(param.getHo()),
                        eqCarNum(param.getCarNum()))
                .fetch();

        for(Tuple tuple : list) {
            CarAndApartmentEntity entity = new CarAndApartmentEntity(tuple.get(qApartment), tuple.get(qCar));
            list2.add(entity);
        }

        return list2;
    }

    private BooleanExpression eqStatus(String status) {
        if (StringUtils.isEmpty(status)) {
            return null;
        }
        return qCar.status.eq(status);
    }

    private BooleanExpression eqCarNum(String carNum) {
        if (StringUtils.isEmpty(carNum)) {
            return null;
        }
        return qCar.carNum.eq(carNum);
    }

    private BooleanExpression eqDong(Integer dong) {
        if (dong == null) {
            return null;
        }
        return qApartment.dong.eq(dong);
    }

    private BooleanExpression eqHo(Integer ho) {
        if (ho == null) {
            return null;
        }
        return qApartment.ho.eq(ho);
    }
}
