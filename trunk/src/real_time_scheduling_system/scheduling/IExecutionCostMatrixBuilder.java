package real_time_scheduling_system.scheduling;

import java.util.ArrayList;

import real_time_scheduling_system.model.Machine;
import real_time_scheduling_system.model.Task;

public interface IExecutionCostMatrixBuilder {
	float PROHIBITED_EXECUTION = -1f;

	public ExecutionCostMatrix buildExecutionCostMatrix(ArrayList<Task> tasks,
			ArrayList<Machine> machines);
}
