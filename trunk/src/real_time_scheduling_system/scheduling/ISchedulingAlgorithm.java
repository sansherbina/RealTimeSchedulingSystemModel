package real_time_scheduling_system.scheduling;

import real_time_scheduling_system.scheduling.a_scheduling_algorithm.TaskScheduling;

public interface ISchedulingAlgorithm {
	public TaskScheduling scheduleTask(ExecutionCostMatrix executionCostMatrix)
			throws IllegalArgumentException;
}
