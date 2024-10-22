package de.tonyb12.AirSense_Backend.modell;

// works like a Response model kinda or kinda not dont know...
public class AirHeatingModell {

    private boolean isAirNeeded;
    private boolean isHeatNeeded;

    private double temperatureOutside;
    private int humidLevelOutside;

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

    //returns the Steps that has to been done
    @Override
    public String toString() {

        if(isAirNeeded){

            return "{ Es wird empfholen zu lueften }" + "\n" + "{ Temperatur aktuell in Muenchen: " + temperatureOutside + " }" + "\n" + "{ Luftfeuchtigkeit aktuell in Muenchen: " + humidLevelOutside + " }";
        }

        if(isHeatNeeded){

            return "{ Es wird empfholen zu heizen }" + "\n" + "{ Temperatur aktuell in Muenchen: " + temperatureOutside + " }" + "\n" + "{ Luftfeuchtigkeit aktuell in Muenchen: " + humidLevelOutside + " }";
        }

        return "{ Temperatur aktuell in Muenchen: " + temperatureOutside + " }" + "\n" + "{ Luftfeuchtigkeit aktuell in Muenchen: " + humidLevelOutside +" }" + "\n" + "{ Aktuell kann keine richtige Aussage getroffen werden was helfen wird.}";
    }
}
