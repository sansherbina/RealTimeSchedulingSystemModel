package real_time_scheduling_system.model;

import real_time_scheduling_system.scheduling.AccessLevel;

public class Task extends AccessLevel{
	private int id;
	private double memmoryCapactity;
	private double processor;
	private float creationTime;
	private float executionTime;
	private float requestedExecutionTime;
	private float workTimePercentage;

	public Task(double memmoryCapacity, double proccessor, float executionTime, float workTimePercentage, int accessLevel){
		super(accessLevel);
		this.workTimePercentage=workTimePercentage;
		this.memmoryCapactity=memmoryCapacity;
		this.processor=proccessor;
		this.executionTime=executionTime;
	}
	
	public float operateTask(float operatingTaskTime){
		executionTime+=operatingTaskTime;
		float restTime=0;
		if(executionTime>requestedExecutionTime){
			restTime=executionTime-requestedExecutionTime;
			executionTime=requestedExecutionTime;
		}
		return restTime;
	}
	
	public boolean isFinishOperatingTask(){
		if(requestedExecutionTime<=executionTime){
			return true;
		}
		return false;
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

	public float getWorkTimePercentage() {
		return workTimePercentage;
	}

	public void setWorkTimePercentage(float workTimePercentage) {
		this.workTimePercentage = workTimePercentage;
	}
	
	
}
