package real_time_scheduling_system.scheduling;

import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;

public class TaskOnMachineRelationCalculator implements ITaskOnMachineRelationCalculator{

	@Override
	public double calculateTaskMachineRelation(Task task,
			MachineConfiguration machineConfiguration) {
		double memmoryRelation=machineConfiguration.getMemmoryCapacity()/task.getMemmoryCapactity();
		double machineProcessors=0;
		for(int i=0;i<machineConfiguration.getProcessors().length;i++){
			machineProcessors+=machineConfiguration.getProcessors()[i];
		}
		double machineRelation=machineProcessors/task.getProcessor();
		double relation=(memmoryRelation+machineRelation)/2;
		return relation;
	}

}
