package com.johyeyeong.parking.adm.controller;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.adm.entity.CarAndApartmentEntity;
import com.johyeyeong.parking.adm.repository.CarAndApartmentRepository;
import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.repository.CarRepository;
import com.johyeyeong.parking.common.ResultMap;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController("AdminRestController")
@RequestMapping("/adm/data")
@RequiredArgsConstructor
public class AdmDataController {
    private final CarRepository carRepository;
    private final CarAndApartmentRepository carAndApartmentRepository;

    @PostMapping("/get-list")
    public ResultMap getCarList(@RequestBody StatusParam param) {
        ResultMap result = new ResultMap();
        try {
            List list = carAndApartmentRepository.findCarAndApartment(param);
            result.setList(list);
            result.setSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            result.setFailure();
        }
        return result;
    }

    @PostMapping("/change/status")
    public ResultMap changeStatus(@RequestBody List<CarEntity> list) {
        ResultMap result = new ResultMap();
        try {
            carRepository.saveAll(list);
            result.setSuccess();
        } catch (Exception e) {
            result.setFailure();
        }
        return result;
    }
}
