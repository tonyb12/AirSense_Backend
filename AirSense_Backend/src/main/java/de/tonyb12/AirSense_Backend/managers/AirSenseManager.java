package de.tonyb12.AirSense_Backend.managers;

import de.tonyb12.AirSense_Backend.modell.AirHeatingModell;
import org.springframework.stereotype.Component;

@Component
public class AirSenseManager {


        AirHeatingModell airHeatingModell = new AirHeatingModell();


       private double calculateSaturationVaporPressure(double temperature){

           return 6.112 * Math.exp((17.67 * temperature) / (temperature + 243.5));
       }



        private double calculateAbsoluteHumidity(double temperature, int humidity) {
             double saturationVaporPressure = 6.112 * Math.exp((17.67 * temperature) / (temperature + 243.5));
            return (saturationVaporPressure * humidity * 2.1674) / (temperature + 273.15);
        }

        private double calculateRequiredTemperature(double initialTemperature,double initialHumidity, double targetHumidity){

           double saturationPressureInitial = calculateSaturationVaporPressure(initialTemperature);
           double absoluteHumidity = saturationPressureInitial * (initialHumidity / 100);

            return (243.5 * Math.log(absoluteHumidity / (6.112 * (targetHumidity / 100.0)))) / (17.67 - Math.log(absoluteHumidity / (6.112 * (targetHumidity / 100.0))));
        }




        private double calculateHumidityDifference(double absoluteHumidityInside, double absoluteHumidityOutside){

            return absoluteHumidityInside - absoluteHumidityOutside;
        }





        //
        public AirHeatingModell isAirOrHeatNeeded(double temperatureInside, int humidLevelInside, double temperatureOutside, int humidLevelOutside) {

            double absoluteHumidityInside = calculateAbsoluteHumidity(temperatureInside, humidLevelInside);
            double absoluteHumidityOutside = calculateAbsoluteHumidity(temperatureOutside, humidLevelOutside);

            double requiredTemperatureForHeating = calculateRequiredTemperature(temperatureInside, humidLevelInside, 58);
            double humidityDifference = calculateHumidityDifference(absoluteHumidityInside,absoluteHumidityOutside);

            airHeatingModell.setRequiredTemperatureForHeating(requiredTemperatureForHeating);
            airHeatingModell.setAbsoluteHumidityDifference(humidityDifference);



            if(humidityDifference > 0 ){

                System.out.println("Humid Difference2: " + humidityDifference);
                airHeatingModell.setAirNeeded(true);
            } else {

                airHeatingModell.setAirNeeded(false);
            }


            if(temperatureInside < requiredTemperatureForHeating){
                airHeatingModell.setHeatNeeded(true);
            }

                airHeatingModell.setTemperatureOutside(temperatureOutside);
                airHeatingModell.setHumidLevelOutside(humidLevelOutside);

                return airHeatingModell;
        }





}
