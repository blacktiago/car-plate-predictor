package com.blacktiago.carplate.demo.engine;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Prediction {

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private boolean allowed;
}
