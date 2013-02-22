package real_time_scheduling_system.data_managment;

public class ModelSettings {
	private int minimumTaskTime;
	private int maximumTaskTime;
	private int inputTaskFlowType;
	private int taskCount;
	private float taskLoadingTimeInterval;
	private float taskLoadingCountBorder;
	private int schedulingAlgorithmType;
	private float modelingTime;
	
	public ModelSettings(int minimumTaskTime, int maximumTaskTime,
			int inputTaskFlowType, int taskCount) {
		super();
		this.minimumTaskTime = minimumTaskTime;
		this.maximumTaskTime = maximumTaskTime;
		this.inputTaskFlowType = inputTaskFlowType;
		this.taskCount = taskCount;
	}
	public int getMinimumTaskTime() {
		return minimumTaskTime;
	}
	public void setMinimumTaskTime(int minimumTaskTime) {
		this.minimumTaskTime = minimumTaskTime;
	}
	public int getMaximumTaskTime() {
		return maximumTaskTime;
	}
	public void setMaximumTaskTime(int maximumTaskTime) {
		this.maximumTaskTime = maximumTaskTime;
	}
	public int getInputTaskFlowType() {
		return inputTaskFlowType;
	}
	public void setInputTaskFlowType(int inputTaskFlowType) {
		this.inputTaskFlowType = inputTaskFlowType;
	}
	public int getTaskCount() {
		return taskCount;
	}
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	
	
    public float getTaskLoadingTimeInterval() {
		return taskLoadingTimeInterval;
	}
	public void setTaskLoadingTimeInterval(float taskLoadingTimeInterval) {
		this.taskLoadingTimeInterval = taskLoadingTimeInterval;
	}
	public float getTaskLoadingCountBorder() {
		return taskLoadingCountBorder;
	}
	public void setTaskLoadingCountBorder(float taskLoadingCountBorder) {
		this.taskLoadingCountBorder = taskLoadingCountBorder;
	}
	
	
	public int getSchedulingAlgorithmType() {
		return schedulingAlgorithmType;
	}
	public void setSchedulingAlgorithmType(int schedulingAlgorithmType) {
		this.schedulingAlgorithmType = schedulingAlgorithmType;
	}
	
	
	
	public float getModelingTime() {
		return modelingTime;
	}
	public void setModelingTime(float modelingTime) {
		this.modelingTime = modelingTime;
	}
	public String toString() {
        String result = "\n--- Model Settings ---";
        result += "\nminimumTaskTime: " + getMinimumTaskTime();
        result += "\nmaximumTaskTime: " + getMaximumTaskTime();
        result += "\ninputTaskFlowType: " + getInputTaskFlowType();
        result += "\ntaskCount: " + getTaskCount();
        return result;
    }	
}
