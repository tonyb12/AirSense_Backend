package de.tonyb12.AirSense_Backend.modell;


// Just a model class
public class Weather {

    private double temperature;
    private int humidLevel;

    public Weather(double temperature, int humidLevel){
        this.temperature = temperature;
        this.humidLevel = humidLevel;
    }

    public Weather(){}

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidLevel() {
        return humidLevel;
    }

    public void setHumidLevel(int humidLevel) {
        this.humidLevel = humidLevel;
    }
}
