package real_time_scheduling_system.scheduling;

public class Task extends AccessLevel{
	private int id;
	private double operateTime;
	private double memmoryCapactity;
	private double processor;
	private int creationTime;
	
	public Task(int accessLevel, int id, double operateTime,
			double memmoryCapactity, double processor, int creationTime) {
		super(accessLevel);
		this.id = id;
		this.operateTime = operateTime;
		this.memmoryCapactity = memmoryCapactity;
		this.processor = processor;
		this.creationTime=creationTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(double operateTime) {
		this.operateTime = operateTime;
	}
	public double getMemmoryCapactity() {
		return memmoryCapactity;
	}
	public void setMemmoryCapactity(double memmoryCapactity) {
		this.memmoryCapactity = memmoryCapactity;
	}
	public double getProcessor() {
		return processor;
	}
	public void setProcessor(double processor) {
		this.processor = processor;
	}
	public int getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(int creationTime) {
		this.creationTime = creationTime;
	}
	
}
