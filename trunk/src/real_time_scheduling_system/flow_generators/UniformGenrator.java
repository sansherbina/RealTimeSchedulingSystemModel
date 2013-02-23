package real_time_scheduling_system.flow_generators;

public class UniformGenrator implements IRandomGenerator {

	@Override
	public double nextRandomValue() {
		return Math.random();
	}

}
