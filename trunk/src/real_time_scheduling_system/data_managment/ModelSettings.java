package real_time_scheduling_system.data_managment;

public class ModelSettings {
	private int minimumTaskTime;
	private int maximumTaskTime;
	private int inputTaskFlowType;
	private int taskCount;
	
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
	
}
