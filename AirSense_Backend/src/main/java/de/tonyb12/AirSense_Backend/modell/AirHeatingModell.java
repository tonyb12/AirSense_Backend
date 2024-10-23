package de.tonyb12.AirSense_Backend.modell;

// works like a Response model kinda or kinda not dont know...
public class AirHeatingModell {

    private boolean isAirNeeded;
    private boolean isHeatNeeded;
    private double requiredTemperatureForHeating;
    private double absoluteHumidityDifference;
    private double temperatureOutside;
    private int humidLevelOutside;

    public double getAbsoluteHumidityDifference() {
        return absoluteHumidityDifference;
    }

    public void setAbsoluteHumidityDifference(double absoluteHumidityDifference) {
        this.absoluteHumidityDifference = absoluteHumidityDifference;
    }

    public double getTemperatureOutside() {
        return temperatureOutside;
    }

    public void setTemperatureOutside(double temperatureOutside) {
        this.temperatureOutside = temperatureOutside;
    }

    public int getHumidLevelOutside() {
        return humidLevelOutside;
    }

    public void setHumidLevelOutside(int humidLevelOutside) {
        this.humidLevelOutside = humidLevelOutside;
    }

    public boolean isAirNeeded() {
        return isAirNeeded;
    }

    public void setAirNeeded(boolean airNeeded) {
        isAirNeeded = airNeeded;
    }

    public boolean isHeatNeeded() {
        return isHeatNeeded;
    }

    public void setHeatNeeded(boolean heatNeeded) {
        isHeatNeeded = heatNeeded;
    }

    public double getRequiredTemperatureForHeating() {
        return requiredTemperatureForHeating;
    }

    public void setRequiredTemperatureForHeating(double requiredTemperatureForHeating) {
        this.requiredTemperatureForHeating = requiredTemperatureForHeating;
    }

    //returns the Steps that has to been done
    @Override
    public String toString() {

        if(isAirNeeded){

            return "{ Es wird empfholen zu lueften }" + "\n" + "{ Temperatur aktuell in Muenchen: " + temperatureOutside + " }" + "\n" + "{ Luftfeuchtigkeit aktuell in Muenchen: " + humidLevelOutside + " }" + "\n" + "{ Durch das Lueften kann die Luftfeuchtigkeit um " + absoluteHumidityDifference + " g/m³ gesenkt werden }";
        }

        if(isHeatNeeded){

            return "{ Es wird empfholen zu heizen }" + "\n" + "{ Temperatur aktuell in Muenchen: " + temperatureOutside + " }" + "\n" + "{ Luftfeuchtigkeit aktuell in Muenchen: " + humidLevelOutside + " }" + "\n" +"{ Empfohlene Raumtemperatur: " + requiredTemperatureForHeating + " }";
        }

        return "{ Temperatur aktuell in Muenchen: " + temperatureOutside + " }" + "\n" + "{ Luftfeuchtigkeit aktuell in Muenchen: " + humidLevelOutside +" }" + "\n" + "{ Aktuell kann keine richtige Aussage getroffen werden was helfen wird.}";
    }


}
