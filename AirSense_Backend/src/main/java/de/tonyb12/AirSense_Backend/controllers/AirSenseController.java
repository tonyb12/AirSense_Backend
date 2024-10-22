package de.tonyb12.AirSense_Backend.controllers;

import de.tonyb12.AirSense_Backend.managers.AirSenseManager;
import de.tonyb12.AirSense_Backend.managers.DiscordMessegingManager;
import de.tonyb12.AirSense_Backend.managers.WeatherCallManager;
import de.tonyb12.AirSense_Backend.modell.AirHeatingModell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AirSenseController {

    @Autowired
    WeatherCallManager weatherCallManager;
    @Autowired
    AirSenseManager airSenseManager;

    @Autowired
    DiscordMessegingManager discordMessegingManager;



    // method for activating the Discord Webhook
    @PostMapping(value = "/webhook")
    public void activateDiscordWebhook(){

        try{
            discordMessegingManager.createDiscordWebhook();
        }catch (Exception e){
            e.printStackTrace();
        }


    }



    // Get rquest for geting the humid Level outside
    @GetMapping(value = "/humidLevel")
    public ResponseEntity<String> getHumidLevelOutside(){

        int humidLevelOutside;

        try{
           humidLevelOutside = weatherCallManager.getHumid();
            if(humidLevelOutside > 0 ){
                String data = "humidLevel Outside: " + humidLevelOutside;
                return new ResponseEntity<>(data,HttpStatus.OK);
            }
        }catch (Exception e){


            e.printStackTrace();
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // Get request to get the temperature outside in munich its hardcoded to munich
    @GetMapping(value = "/temperature")
    public ResponseEntity<String> getTemperatureOutside(){

        double temperatureOutside;

        try{
            temperatureOutside = weatherCallManager.getTemperature();
            if(temperatureOutside > 0 ){
                String data = "temperature Outside: " + temperatureOutside;
                return new ResponseEntity<>(data,HttpStatus.OK);
            }
        }catch (Exception e){


            e.printStackTrace();
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // get request to get to know if u have to heat or air
    @GetMapping(value = "/heatingOrAir")
    public ResponseEntity<String> getHeatingOrAir(@RequestParam(name = "temperatureInside") double temperatureInside, @RequestParam(name = "humidLevelInside") int humidLevelInside) {

        try{

            AirHeatingModell airHeatingModell = new AirHeatingModell();

            airHeatingModell = airSenseManager.isAirOrHeatNeeded(temperatureInside,humidLevelInside, weatherCallManager.getTemperature(), weatherCallManager.getHumid());



            discordMessegingManager.sendDiscordAlert(airHeatingModell.toString());

            return new ResponseEntity<>(airHeatingModell.toString(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
