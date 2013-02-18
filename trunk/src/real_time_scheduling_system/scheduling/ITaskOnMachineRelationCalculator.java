package real_time_scheduling_system.scheduling;

import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;

public interface ITaskOnMachineRelationCalculator {
	public double calculateTaskMachineRelation(Task task, MachineConfiguration machineConfiguration);
}
