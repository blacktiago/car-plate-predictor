package com.blacktiago.carplate.demo.engine;


import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class PlateRestrictionConfig {

    @Setter
    Map<String, HashMap<String, String>> time = new HashMap<>();

    @Setter
    Map<Integer, ArrayList<Integer>> days = new HashMap<>();

}
