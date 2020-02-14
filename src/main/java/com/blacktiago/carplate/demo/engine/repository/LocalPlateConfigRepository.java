package com.blacktiago.carplate.demo.engine.repository;

import com.blacktiago.carplate.demo.engine.PlateRestrictionConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@Slf4j
public class LocalPlateConfigRepository implements PlateConfigRepository{

    @Override
    public PlateRestrictionConfig loadConfig() {

        PlateRestrictionConfig config = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Resource resource = new ClassPathResource("config.json");
            File file = resource.getFile();

            config = objectMapper.readValue(file, PlateRestrictionConfig.class);
            
        } catch (IOException e) {
            log.error("Unable to load plate predictor configuration ", e);
        }

        return config;
    }

}
