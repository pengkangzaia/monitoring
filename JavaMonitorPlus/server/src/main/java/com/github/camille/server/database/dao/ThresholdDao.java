package com.github.camille.server.database.dao;

import com.github.camille.server.database.entity.data.Threshold;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThresholdDao {

    Threshold getThresholdByAddress(String address);



}
