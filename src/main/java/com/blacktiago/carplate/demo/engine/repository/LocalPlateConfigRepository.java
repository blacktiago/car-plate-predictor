package com.blacktiago.carplate.demo.engine.repository;

import com.blacktiago.carplate.demo.engine.PlateRestrictionConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class LocalPlateConfigRepository implements PlateConfigRepository{

    @Override
    public PlateRestrictionConfig loadConfig() {

        PlateRestrictionConfig config = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileInputStream jsonConfig = new FileInputStream("./config.json");
            config = objectMapper.readValue(jsonConfig, PlateRestrictionConfig.class);
            
        } catch (IOException e) {
            log.error("Unable to load plate predictor configuration ", e);
        }

        return config;
    }

}
