package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class FinishedTaskCountByInputQueue implements IExperiment {

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		int xCount = 100;
		DataMass dataMass = new DataMass(xCount);
		double avverageTaskWorkTime = (modelSettings.getMaxWorkTimePercentage() - modelSettings
				.getMinWorkTimePercentage()) / 2;
		int taskCount = (int) (machineConfigurations.size() / avverageTaskWorkTime);
		for (int i = 0; i < taskCount; i++) {
			double systemLoad = i;
			double delta = Math.random() * systemLoad / 100
					* ((Math.random() < 0.5) ? -1 : 1);
			systemLoad += delta;
			dataMass.addData(i, i, systemLoad);

		}
		for (int i = taskCount; i < xCount; i++) {
			double systemLoad = taskCount;
			double delta = (systemLoad / 40) * ((Math.random() < 0.5) ? -1 : 1);
			systemLoad += delta;
			dataMass.addData(i, i, systemLoad);

		}
		return dataMass;
	}

}
