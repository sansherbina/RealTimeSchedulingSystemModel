package real_time_scheduling_system.flow_generators;

import java.util.Random;

public class NormalGenerator implements IRandomGenerator {
	private double m;
	private double sigma;
	private Random random;

	public NormalGenerator(double m, double sigma) {
		this.m = m;
		this.sigma = sigma;
		this.random = new Random();
	}

	@Override
	public double nextRandomValue() {
		double value=m + random.nextGaussian() * sigma;
		if(value<=0 || value>2*m){
			return nextRandomValue();
		}else{
			return value;
		}
	}
	
}
