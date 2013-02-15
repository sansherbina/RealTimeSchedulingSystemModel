package real_time_scheduling_system.model;

public class ExecutingTaskResult {
	public enum ExecutingTastStatus{
		EXECUTE_SUCCESFULL, EXECUTING_ERROR, SCHEDULING_ERROR
	}
	private Task task;
	private ExecutingTastStatus executingTaskStatus;
}
