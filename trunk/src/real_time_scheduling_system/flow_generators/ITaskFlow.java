package real_time_scheduling_system.flow_generators;

import real_time_scheduling_system.scheduling.Task;

public interface ITaskFlow {
	public Task[] generateTasks();
}
