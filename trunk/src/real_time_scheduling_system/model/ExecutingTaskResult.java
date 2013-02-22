package real_time_scheduling_system.model;

public class ExecutingTaskResult {
	public enum ExecutingTaskStatus {
		EXECUTE_SUCCESFULL, EXECUTING_ERROR, SCHEDULING_ERROR
	}

	private Task task;
	private ExecutingTaskStatus executingTaskStatus;

	public ExecutingTaskResult(Task task,
			ExecutingTaskStatus executingTaskStatus) {
		super();
		this.task = task;
		this.executingTaskStatus = executingTaskStatus;
	}

	public Task getTask() {
		return task;
	}

	public ExecutingTaskStatus getExecutingTaskStatus() {
		return executingTaskStatus;
	}
}
