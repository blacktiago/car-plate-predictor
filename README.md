# car-plate-predictor
Spring boot app to find if a car plate number is allow or not to go out

In order to execute this program you need to provide a json config file like this
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

To execute this program provide the plate, date and time you want to know if is allowed or not to drive

```shell
java -jar CarPlatePredictor-1.0.0-M1.jar -p "PFB 2317" -d "13-02-2020" -t "8:30"
```