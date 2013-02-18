package real_time_scheduling_system.model;

import java.util.ArrayList;

public interface IExecutedTaskHandler {
	public void handleExecutedTasks(ArrayList<ExecutingTaskResult> executedTasks);
}
