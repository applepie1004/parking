package com.johyeyeong.parking.apply.repository;


import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.repository.querydsl.CarRepositoryCustom;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, String>, CarRepositoryCustom {
    /* QueryDsl */
    @Override
    CarEntity findByAptIdAndCarNum(UUID aptId, String carNum);

    @Override
    List<Tuple> findCarAndApartment();

    /* 일반 JPA */
    int countAllByAptIdAndStatusIs(UUID aptId,String status);

    List<CarEntity> findAllByStatusIs(String status);

}
