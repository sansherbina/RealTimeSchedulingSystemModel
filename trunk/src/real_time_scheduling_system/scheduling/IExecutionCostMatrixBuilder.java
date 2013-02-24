package real_time_scheduling_system.scheduling;


import java.util.List;

import real_time_scheduling_system.model.Machine;
import real_time_scheduling_system.model.Task;

public interface IExecutionCostMatrixBuilder {
	float PROHIBITED_EXECUTION = -1f;

	public ExecutionCostMatrix buildExecutionCostMatrix(List<Task> tasks,
			List<Machine> machines);
}
