package real_time_scheduling_system.scheduling;

import java.util.HashMap;

import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;

public interface ISchedulingAlgorithm {
	public HashMap<Integer, Integer> scheduleTask(Task[] tasks, MachineConfiguration[] machines) throws IllegalArgumentException;
}
