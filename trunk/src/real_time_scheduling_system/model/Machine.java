package real_time_scheduling_system.model;

import java.util.ArrayList;

public class Machine {
	private MachineConfiguration machineConfiguration;
	private ArrayList<Task> executingTasks;
	public Machine(MachineConfiguration machineConfiguration) {
		super();
		this.machineConfiguration = machineConfiguration;
		executingTasks=new ArrayList<Task>();
	}
	
}
