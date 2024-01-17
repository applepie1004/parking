package com.johyeyeong.parking.apply.repository;

import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Object> {
    ApartmentEntity findByDongAndHo(int dong, int ho);
}
