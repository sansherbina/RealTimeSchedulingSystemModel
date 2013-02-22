package real_time_scheduling_system.experiment;

import java.util.ArrayList;

import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.data_managment.DataMass;

public interface IExperiment {
	public DataMass makeExperiment(ModelSettings modelSettings,
			ArrayList<MachineConfiguration> machineConfigurations);
}
