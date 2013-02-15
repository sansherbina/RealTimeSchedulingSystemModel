package real_time_scheduling_system.model;

import real_time_scheduling_system.scheduling.AccessLevel;

public class Task extends AccessLevel{
	private int id;
	private double memmoryCapactity;
	private double processor;
	private float creationTime;
	private float executionTime;
	private float requestedExecutionTime;
	
	public Task(int id, 
			double memmoryCapactity, double processor, float creationTime,
			float executionTime, float requestedExecutionTime, int accessLevel) {
		super(accessLevel);
		this.id = id;
		this.memmoryCapactity = memmoryCapactity;
		this.processor = processor;
		this.creationTime = creationTime;
		this.executionTime = executionTime;
		this.requestedExecutionTime = requestedExecutionTime;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public float getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(float creationTime) {
		this.creationTime = creationTime;
	}

	public float getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(float executionTime) {
		this.executionTime = executionTime;
	}

	public float getRequestedExecutionTime() {
		return requestedExecutionTime;
	}

	public void setRequestedExecutionTime(float requestedExecutionTime) {
		this.requestedExecutionTime = requestedExecutionTime;
	}
	
}
