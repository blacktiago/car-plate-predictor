# Car plate predictor

[![Build Status](https://travis-ci.com/blacktiago/car-plate-predictor.svg?branch=master)](https://travis-ci.com/blacktiago/car-plate-predictor) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=blacktiago_car-plate-predictor&metric=alert_status)](https://sonarcloud.io/dashboard?id=blacktiago_car-plate-predictor)


In order to execute this program you need to provide a json config like this
```json
{
  "time": {
    "am": {
      "begin": "7:30",
      "end": "9:30"
    },
    "pm": {
      "begin": "16:00",
      "end": "19:30"
    }
  },
  "days": {
    "2": [
      1,
      2
    ],
    "3": [
      3,
      4
    ],
    "4": [
      5,
      6
    ],
    "5": [
      7,
      8
    ],
    "6": [
      9,
      0
    ]
  }
}
```

Place the above config file at the same path where the program will be executed.


### Run as rest service
If you want to execute car plate predictor as rest service use the following command:
```shell
java -jar CarPlatePredictor-1.0.0-M2.jar
```

then you could consume it on port **8080/predict** sending plate, date and time as bellow:

```
http://localhost:8080/predict?plate=PRD%202348&date=08-02-2020&time=8:30
```

### React web app

At root uri **http://localhost:8080/** there is a react web app to test our rest endpoint, the source code is [here](https://github.com/blacktiago/car-plate-predictor-frontend) 

### Console run and die

Ypu could also execute it form console to test values, just provide plate, date and time. Argument **-dry** indicates print result and exit.

```shell
java -jar CarPlatePredictor-1.0.0-M2.jar -dry -p "PFB 2317" -d "13-02-2020" -t "8:30"
```




Follow me on [@blacktiago](https://twitter.com/blacktiago)
