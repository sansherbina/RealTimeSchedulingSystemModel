package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.data_managment.DataMass;

public interface IExperiment {
	enum ExperimentTypes {
		SYSTEM_LOAD_BY_INPUT_QUEUE("SystemLoadingByInputQueue",
				"InputQueueSize", "SystemLoading");
		public String name;
		public String xName;
		public String yName;

		private ExperimentTypes(String name, String xName, String yName) {
			this.name = name;
			this.xName = xName;
			this.yName = yName;
		}
	}

	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations);
}
