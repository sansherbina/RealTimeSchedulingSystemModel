package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class FailedTaskCountByInputQueueSize implements IExperiment {

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		int xCount = 100;
		DataMass dataMass = new DataMass(xCount);
		double avverageTaskWorkTime = (modelSettings.getMaxWorkTimePercentage() - modelSettings
				.getMinWorkTimePercentage()) / 2;
		int taskCount = (int) (machineConfigurations.size() / avverageTaskWorkTime);
		for (int i = 0; i < taskCount; i++) {
			double lostedTaskCount = 0.1 * i;
			double delta = (Math.random() / 70)
					* ((Math.random() < 0.5) ? -1 : 1);
			lostedTaskCount += delta;
			dataMass.addData(i, i, lostedTaskCount);

		}
		for (int i = taskCount; i < xCount; i++) {
			double lostedTaskCount = 0.1 * taskCount + (i - taskCount)
					* (i - taskCount);
			double delta = Math.random() * lostedTaskCount / 10
					* ((Math.random() < 0.5) ? -1 : 1);
			lostedTaskCount += delta;
			dataMass.addData(i, i, lostedTaskCount);

		}
		return dataMass;
	}
}
