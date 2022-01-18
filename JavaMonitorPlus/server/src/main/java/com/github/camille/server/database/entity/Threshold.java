package com.github.camille.server.database.entity;

import lombok.Data;

@Data
public class Threshold {

    private int id;
    private String address;
    private Double threshold;

}
