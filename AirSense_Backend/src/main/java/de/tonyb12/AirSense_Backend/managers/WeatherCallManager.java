package de.tonyb12.AirSense_Backend.managers;

import de.tonyb12.AirSense_Backend.modell.Weather;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// This class handles the api calls to api.weatherapi... i know its hard coded i will fix that sometime...
@Component
public class WeatherCallManager {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url.template}")
    private String urlTemplate;

    @Value("${weather.api.city}")
    private String city;

    private RestTemplate restTemplate = new RestTemplate();;

    private Weather weather = new Weather();

    @Autowired
    public WeatherCallManager(){

    }

    // gets the humid outside in Munich
    public int getHumid() {

        try {
            String requestUrl = String.format(urlTemplate, apiKey, city);
            String response = restTemplate.getForObject(requestUrl, String.class);
            JSONObject jsonResponse = new JSONObject(response);
            weather.setHumidLevel(jsonResponse.getJSONObject("current").getInt("humidity"));


            return weather.getHumidLevel();

        } catch (Exception e) {

            e.printStackTrace();

            return 0;
        }
    }

        // gets the Temperature outside in Munich
        public double getTemperature(){

            try{
                String requestUrl = String.format(urlTemplate,apiKey, city);
                String response = restTemplate.getForObject(requestUrl,String.class);
                JSONObject jsonResponse = new JSONObject(response);
                weather.setTemperature(jsonResponse.getJSONObject("current").getInt("temp_c"));
                return weather.getTemperature();

            }catch (Exception e){

                e.printStackTrace();

                return 0;
            }


    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
