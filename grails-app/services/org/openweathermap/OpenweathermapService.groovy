package org.openweathermap

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import santander.Clima

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@CompileStatic
class OpenweathermapService implements GrailsConfigurationAware{
    String appid
    String cityId
    BlockingHttpClient client

    @Override
    void setConfiguration(Config co) {
        //url ejemplo: https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=439d4b804bc8187953eb36d2a8c26a02
        setupHttpClient(co.getProperty('openweather.url', String, 'https://samples.openweathermap.org'))
        //setupHttpClient(co.getProperty('openweather.url', String, 'http://api.openweathermap.org'))
        appid = co.getProperty('openweather.appid', String)
        cityId = co.getProperty('openweather.cityId', String)
    }

    void setupHttpClient(String url) {
        this.client = HttpClient.create(url.toURL()).toBlocking()
    }

     void cargarDatosClima() {
        DailyForecast Datos = dailyForecast()
        for (Forecast forecast : Datos.forecasts) {
            new Clima(temperatura: forecast.info.temp, dateTime: LocalDateTime.ofInstant(Instant.ofEpochMilli(forecast.time), ZoneId.systemDefault())).save()
        }
    }

    DailyForecast dailyForecast(){
        try {
            HttpRequest request = HttpRequest.GET(testDailyForecastUri())
            //HttpRequest request = HttpRequest.GET(dailyForecastUri(cityId))
            return client.retrieve(request, DailyForecast)

        } catch (HttpClientResponseException e) {
            return null
        }
    }

    URI testDailyForecastUri(){
        UriBuilder uriBuilder = UriBuilder.of('/data/2.5/forecast')
                .queryParam('id', '524901')
                .queryParam('appid', '439d4b804bc8187953eb36d2a8c26a02')
        uriBuilder.build()
    }

    URI dailyForecastUri(String cityId){
        UriBuilder uriBuilder = UriBuilder.of('/data/2.5/forecast')
                .queryParam('id', "${cityId}".toString())
                .queryParam('units', 'metric')
                .queryParam('lang', 'es')
                .queryParam('appid', appid)
        uriBuilder.build()
    }

}
