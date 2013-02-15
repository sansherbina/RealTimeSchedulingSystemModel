package real_time_scheduling_system.flow_generators;

import java.util.ArrayList;

import real_time_scheduling_system.model.Task;

public interface ITaskFlow {
	public ArrayList<Task> generateTasks();
}
