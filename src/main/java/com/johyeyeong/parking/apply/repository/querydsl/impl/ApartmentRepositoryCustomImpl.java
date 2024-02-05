package com.johyeyeong.parking.apply.repository.querydsl.impl;

import com.johyeyeong.parking.apply.entity.QApartmentEntity;
import com.johyeyeong.parking.apply.repository.querydsl.ApartmentRepositoryCustom;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class ApartmentRepositoryCustomImpl implements ApartmentRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    QApartmentEntity qApartment = QApartmentEntity.apartmentEntity;


    @Override
    public List<Integer> getAllDong() {
        List<Integer> list = jpaQueryFactory.
                select(qApartment.dong).
                distinct().
                from(qApartment).
                orderBy(new OrderSpecifier(Order.ASC, qApartment.dong)).
                fetch();
        return list;
    }

    @Override
    public List<Integer> getAllHoByDong(int dong) {
        List<Integer> list = jpaQueryFactory.
                select(qApartment.ho).
                from(qApartment).
                where(qApartment.dong.eq(dong)).
                orderBy(new OrderSpecifier(Order.ASC, qApartment.ho)).fetch();

        return list;
    }
}
