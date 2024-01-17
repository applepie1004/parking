package com.johyeyeong.parking.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRtParam {
    private int dong;
    private int ho;
    private String carNum;
}
