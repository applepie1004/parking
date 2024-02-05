package com.johyeyeong.parking.apply.repository;

import ch.qos.logback.core.read.ListAppender;
import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import com.johyeyeong.parking.apply.repository.querydsl.ApartmentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Object>, ApartmentRepositoryCustom {
    ApartmentEntity findByDongAndHo(int dong, int ho);


    /* queryDsl */
    @Override
    List<Integer> getAllDong();

    @Override
    List<Integer> getAllHoByDong(int dong);
}

