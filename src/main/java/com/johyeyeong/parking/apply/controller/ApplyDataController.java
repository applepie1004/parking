package com.johyeyeong.parking.apply.controller;

import com.johyeyeong.parking.apply.dto.CarRtParam;
import com.johyeyeong.parking.apply.entity.ApartmentEntity;
import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.entity.CarKeyEntity;
import com.johyeyeong.parking.apply.repository.ApartmentRepository;
import com.johyeyeong.parking.apply.repository.CarQtRtRepository;
import com.johyeyeong.parking.apply.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/apply/data")
public class ApplyDataController {

    private final CarQtRtRepository carQtRtRepository;
    private final ApartmentRepository apartmentRepository;
    private final CarRepository carRepository;

    @PostMapping("/get-qt-rt")
    public Map<String, Object> getCarQtRT() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("RESULT", carQtRtRepository.findAll());

        return resultMap;
    }
    @PostMapping("/get-car-rt")
    public Map<String, Object> getCarRt(@RequestBody CarRtParam carRtParam) {
        Map<String, Object> resultMap = new HashMap<>();

        ApartmentEntity entity = apartmentRepository.findByDongAndHo(carRtParam.getDong(), carRtParam.getHo());
        int count = carRepository.countAllByAptIdAndStatusIs(entity.getAptId(),"Y");

        resultMap.put("RESULT", count);

        return resultMap;
    }
    @PostMapping("/car-exist")
    public Map<String, Object> getCarByApt(@RequestBody CarRtParam carRtParam) {
        Map<String, Object> resultMap = new HashMap<>();

        ApartmentEntity entity = apartmentRepository.findByDongAndHo(carRtParam.getDong(), carRtParam.getHo());

        CarEntity car = null;
        if(entity != null) {
            car = carRepository.findByAptIdAndCarNum(entity.getAptId(), carRtParam.getCarNum());
        }

        CarEntity finalCar = car;

        CarEntity isSameCarExist = carRepository.findByCarNum(carRtParam.getCarNum());
        resultMap.put("RESULT", new HashMap<>(){{ put("isCarExist", finalCar != null ? true : false );
            put("isOwnerExist", entity != null ? true : false );
            put("isSameCarExist", finalCar == null  && isSameCarExist != null ? true : false);
        }});

        return resultMap;
    }


    @PostMapping("/save-car")
    public Map<String, Object> applyCarByAptId(@RequestBody CarRtParam carRtParam) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            ApartmentEntity entity = apartmentRepository.findByDongAndHo(carRtParam.getDong(), carRtParam.getHo());

            Timestamp ts = new Timestamp(System.currentTimeMillis());
            CarEntity car = new CarEntity();
            car.setAptId(entity.getAptId());
            car.setCarNum(carRtParam.getCarNum());
            car.setStatus("W");
            car.setRegDt(ts);

            carRepository.save(car); // 기본 jpa 구문
            resultMap.put("RESULT", "SUCCESS");
        } catch (Exception e) {
            resultMap.put("RESULT", "FAILURE");
        }

        return resultMap;
    }


}
