package com.johyeyeong.parking.apply.repository;


import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.repository.querydsl.CarRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, String>, CarRepositoryCustom {
    /* QueryDsl */
    @Override
    CarEntity findByAptIdAndCarNum(UUID aptId, String carNum);


    /* 일반 JPA */
    int countAllByAptIdAndStatusIs(UUID aptId,String status);

}
