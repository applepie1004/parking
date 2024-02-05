package com.johyeyeong.parking.apply.repository.querydsl;

import java.util.List;

public interface ApartmentRepositoryCustom {

    List<Integer> getAllDong();
    List<Integer> getAllHoByDong(int dong);
}
