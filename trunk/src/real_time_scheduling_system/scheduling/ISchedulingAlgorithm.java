package real_time_scheduling_system.scheduling;

import java.util.HashMap;

public interface ISchedulingAlgorithm {
	public HashMap<Integer, Integer> scheduleTask(Task[] tasks, Machine[] machines) throws IllegalArgumentException;
}
