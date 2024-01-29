package com.johyeyeong.parking.adm.controller;

import com.johyeyeong.parking.adm.dto.StatusParam;
import com.johyeyeong.parking.apply.entity.CarEntity;
import com.johyeyeong.parking.apply.repository.CarRepository;
import groovyjarjarantlr4.v4.codegen.model.chunk.ListLabelRef;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        } catch (Exception e) {
            resultMap.put("RESULT", "FAILURE");
        }
        return resultMap;
    }
}
