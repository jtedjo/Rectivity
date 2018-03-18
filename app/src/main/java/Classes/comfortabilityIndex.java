package Classes;
import java.lang.Math;
/**
 * Created by Jon T on 3/18/2018.
 */
//humidex calculations
// more info can be found    https://planetcalc.com/5673/
//air temperature needs to be in celsius
    /*

    20 - 29: No discomfort
    30 - 39: Some discomfort
    40 - 45: Great discomfort; avoid exertion
    *46 and over: Dangerous; possible heat stroke

     */
//humidity in percentage,a.k.a .55 = 55
public class comfortabilityIndex {

    private double airTemperature;
    private double relativeHumidity;

    public comfortabilityIndex(double temperature, double humidity){
        airTemperature = temperature;
        relativeHumidity = humidity;

    }
    public double humidex(){
        double calculations = 0;
        double superscript;
        superscript = 7.5 * (airTemperature/(237.7+airTemperature));
        calculations = airTemperature+5/9*((6.112 *java.lang.Math.pow(10, superscript) * (relativeHumidity/100)) - 10);
        return calculations;
    }
}
