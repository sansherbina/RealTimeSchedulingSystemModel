package real_time_scheduling_system.model;


import java.util.List;

public interface IExecutedTaskHandler {
	public void handleExecutedTasks(List<ExecutingTaskResult> executedTasks);
}
