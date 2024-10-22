package de.tonyb12.AirSense_Backend.managers;

import de.tonyb12.AirSense_Backend.modell.AirHeatingModell;
import org.springframework.stereotype.Component;

@Component
public class AirSenseManager {


        AirHeatingModell airHeatingModell = new AirHeatingModell();


        // help method for getting dewPoint(Taupunkt)
        private double calculateDewPoint(double temperature, int humidLevel){

            double a = 17.27;
            double b = 237.7;

            double alpha = ((a * temperature) / (b + temperature)) + Math.log(humidLevel / 100.0);

            return (b * alpha) / (a - alpha);
        }

        // help method for getting the relative humid level
        private double calculateRelativeHumidity(double temperature, double dewPoint){

            double exponentTemp = (17.27 * temperature) / (temperature + 237.7);
            double exponentDewPoint = (17.27 * dewPoint) / (dewPoint + 237.7);

            return 100 * (Math.exp(exponentDewPoint) / Math.exp(exponentTemp));
        }


        // function that says if u have to air ur room not quite sure if it works correctly lol but online tester says the same as this function soo.....
        private boolean isAirNeeded(double temperatureInside,int humidLevelInside, double temperatureOutside, int humidLevelOutside ){

            double dewPointInside = calculateDewPoint(temperatureInside, humidLevelInside);
            double dewPointOutside = calculateDewPoint(temperatureOutside, humidLevelOutside);

            double relativeHumidLevelInside = calculateRelativeHumidity(temperatureInside, dewPointInside);
            double relativeHumidLevelOutside = calculateRelativeHumidity(temperatureOutside, dewPointOutside);


            boolean shouldAir = relativeHumidLevelInside >  humidLevelOutside;
            return shouldAir && relativeHumidLevelOutside < 80;
        }


        // not sure if 21 and 60 is a good reference for this test
        private boolean isHeatingNeeded(double temperatureInside, int humidLevelInside){

            return (temperatureInside < 21 && humidLevelInside > 60);
        }


        // self explained
        public AirHeatingModell isAirOrHeatNeeded(double temperatureInside, int humidLevelInside, double temperatureOutside, int humidLevelOutside){

            boolean air = isAirNeeded(temperatureInside,humidLevelInside,temperatureOutside,humidLevelOutside);
            boolean heat = isHeatingNeeded(temperatureInside, humidLevelInside);

            airHeatingModell.setHumidLevelOutside(humidLevelOutside);
            airHeatingModell.setTemperatureOutside(temperatureOutside);

            if(air){
                airHeatingModell.setAirNeeded(true);
                System.out.println("Es sollte gelueftet werden");
            }

            if(!air){
                airHeatingModell.setAirNeeded(false);
                System.out.println("Lueften ist nicht empfholen");
            }

            if(heat){
                airHeatingModell.setHeatNeeded(true);
                System.out.println("Heizen ist notwendig");
            }

            if (!heat){
                airHeatingModell.setHeatNeeded(false);
                System.out.println("Heizen ist nicht notwendig");
            }

            return airHeatingModell;
        }



}
