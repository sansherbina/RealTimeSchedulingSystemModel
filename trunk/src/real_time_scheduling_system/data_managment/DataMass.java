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

    public DataMass(int experimentCount){
    	if(experimentCount<=0){
    		throw new IllegalArgumentException();
    	}
    	this.Xmass=new double[experimentCount];
    	this.Ymass=new double[experimentCount];
    }
    
    public DataMass(double[] xmass, double[] ymass) {
        Xmass = xmass;
        Ymass = ymass;
    }
    
    public void addData(int position, double xValue, double yValue){
    	if(position<0){
    		throw new IllegalArgumentException();
    	}
    	this.Xmass[position]=xValue;
    	this.Ymass[position]=yValue;
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

	@Override
	public String toString() {
		String res="DataMass";
		System.out.println();
		for(int i=0;i<Xmass.length;i++){
			res+="X="+Xmass[i]+" Y="+Ymass[i]+'\n';
		}
		return res;
	}
    
}
