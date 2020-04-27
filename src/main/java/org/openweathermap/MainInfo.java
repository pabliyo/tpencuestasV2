package org.openweathermap;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.math.BigDecimal;

@Introspected
public class MainInfo {

    @JsonProperty("temp")
    BigDecimal temp;

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }
/*
    {
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
    */
}
