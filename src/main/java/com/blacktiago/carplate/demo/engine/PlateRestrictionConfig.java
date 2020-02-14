package com.blacktiago.carplate.demo.engine;


import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class PlateRestrictionConfig {

    @Setter
    HashMap<String, HashMap<String, String>> time;

    @Setter
    HashMap<Integer, ArrayList<String>> days;

}
