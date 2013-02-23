package real_time_scheduling_system.model;


import java.util.List;

public class ExecutedTasksHandler implements IExecutedTaskHandler {
	private int succesfullyExecutedTasksCount;
	private int unscheduledTasksCount;
	private int executionErrorsCount;

	public ExecutedTasksHandler() {
	}

	public void resetStatistic() {
		this.succesfullyExecutedTasksCount = 0;
		this.unscheduledTasksCount = 0;
		this.executionErrorsCount = 0;
	}

	@Override
	public void handleExecutedTasks(List<ExecutingTaskResult> executedTasks) {
		if (executedTasks == null) {
			return;
		}
		for (ExecutingTaskResult executingTaskResult : executedTasks) {
			switch (executingTaskResult.getExecutingTaskStatus()) {
			case EXECUTE_SUCCESFULL:
				succesfullyExecutedTasksCount++;
				break;
			case EXECUTING_ERROR:
				executionErrorsCount++;
				break;
			case SCHEDULING_ERROR:
				unscheduledTasksCount++;
				break;
			}
		}
	}

	public int getSuccesfullyExecutedTasksCount() {
		return succesfullyExecutedTasksCount;
	}

	public int getUnscheduledTasksCount() {
		return unscheduledTasksCount;
	}

	public int getExecutionErrorsCount() {
		return executionErrorsCount;
	}
}
