package real_time_scheduling_system.data_managment;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 19.02.13
 * Time: 19:37
 */
public class DataMass {
    private double[] Xmass;
    private double[] Ymass;

    public DataMass(double[] xmass, double[] ymass) {
        Xmass = xmass;
        Ymass = ymass;
    }

    public double[] getXmass() {
        return Xmass;
    }

    public void setXmass(double[] xmass) {
        Xmass = xmass;
    }

    public double[] getYmass() {
        return Ymass;
    }

    public void setYmass(double[] ymass) {
        Ymass = ymass;
    }

    public int getSize() {
        return Xmass.length;
    }
}
