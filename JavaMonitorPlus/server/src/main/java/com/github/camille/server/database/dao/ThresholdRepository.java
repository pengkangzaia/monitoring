package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.Threshold;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThresholdRepository {

    Threshold getThresholdByAddress(String address);



}
