package real_time_scheduling_system.scheduling;

import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;

public interface IExecutionCostMatrixBuilder {
	float PROHIBITED_EXECUTION=-1f;
	public ExecutionCostMatrix buildExecutionCostMatrix(Task[] tasks, MachineConfiguration[] machines);
}
