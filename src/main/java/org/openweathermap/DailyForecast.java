package org.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class DailyForecast {
    //api docs: https://openweathermap.org/forecast5
    @JsonProperty("list")
    List<Forecast> forecasts;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }
/*JSON Ejemplo
    {
  "cod": "200",
  "message": 0,
  "cnt": 40,
  "list": [
    {
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

    ...

"city": {
    "id": 2643743,
    "name": "London",
    "coord": {
      "lat": 51.5073,
      "lon": -0.1277
    },
    "country": "GB",
    "timezone": 0,
    "sunrise": 1578384285,
    "sunset": 1578413272
  }
}
 */
}
