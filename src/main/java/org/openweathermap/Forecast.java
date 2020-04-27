package org.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class Forecast {

    @JsonProperty("dt")
    long time;
    @JsonProperty("main")
    MainInfo info;
    @JsonProperty("dt_txt")
    String dateAsText;

    public MainInfo getInfo() {
        return info;
    }

    public void setInfo(MainInfo info) {
        this.info = info;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDateAsText() {
        return dateAsText;
    }

    public void setDateAsText(String dateAsText) {
        this.dateAsText = dateAsText;
    }
/*
    JSON Example
    "dt": 1578409200,
    "main": {
        "temp": 284.92,
        "feels_like": 281.38,
        "temp_min": 283.58,
        "temp_max": 284.92,
        "pressure": 1020,
        "sea_level": 1020,
        "grnd_level": 1016,
        "humidity": 90,
        "temp_kf": 1.34
      },
    "weather": [
        {
          "id": 804,
          "main": "Clouds",
          "description": "overcast clouds",
          "icon": "04d"
        }
      ],
    "clouds": {
        "all": 100
      },
      "wind": {
        "speed": 5.19,
        "deg": 211
      },
      "sys": {
        "pod": "d"
      },
      "dt_txt": "2020-01-07 15:00:00"
    },
     */
}
