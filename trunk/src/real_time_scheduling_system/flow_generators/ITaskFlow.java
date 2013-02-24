package real_time_scheduling_system.flow_generators;

import java.util.List;

import real_time_scheduling_system.model.Task;

public interface ITaskFlow {
	public List<Task> generateTasks(double time);
}
