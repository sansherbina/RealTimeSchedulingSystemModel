package real_time_scheduling_system.scheduling;

public class Machine extends AccessLevel{
	private int id;
	private double memmoryCapacity;
	private double[] processors;
	
	public Machine(int id, double memmoryCapacity, double[] processors, int accessLevel) {
		super(accessLevel);
		this.id = id;
		this.memmoryCapacity = memmoryCapacity;
		this.processors = processors;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getMemmoryCapacity() {
		return memmoryCapacity;
	}
	public void setMemmoryCapacity(double memmoryCapacity) {
		this.memmoryCapacity = memmoryCapacity;
	}
	public double[] getProcessors() {
		return processors;
	}
	public void setProcessors(double[] processors) {
		this.processors = processors;
	}
	
}
