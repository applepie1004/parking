package com.johyeyeong.parking.adm.controller;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.repository.CarRepository;
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
public class DataController {
    private final CarRepository carRepository;

    @PostMapping("/get-list")
    public Map<String, Object> getCarList(@RequestBody StatusParam param) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<CarEntity> list = null;
            if(param != null && param.getStatus() != null && !"".equals(param.getStatus())) {
                list = carRepository.findAllByStatusIs(param.getStatus());
            } else {
                list = carRepository.findAll();
            }
            resultMap.put("list", list);
            resultMap.put("RESULT", "SUCCESS");

            for(Tuple tuple : carRepository.findCarAndApartment()) {
                log.info("{}", tuple);
            }

        } catch (Exception e) {
            resultMap.put("RESULT", "FAILURE");
        }
        return resultMap;
    }

    @PostMapping("/change/status")
    public Map<String, Object> changeStatus(@RequestBody List<CarEntity> list) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            carRepository.saveAll(list);
            resultMap.put("RESULT", "SUCCESS");
        } catch (Exception e) {
            resultMap.put("RESULT", "FAILURE");
        }
        return resultMap;
    }
}
