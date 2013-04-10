package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class FailedTaskCountProbabilityByInputQueueSize implements IExperiment {

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
			double y=lostedTaskCount/500;
			if(y<0){
				y=0;
			}
			dataMass.addData(i, i, y);

		}
		for (int i = taskCount; i < xCount; i++) {
			double lostedTaskCount = (i - taskCount);
			double delta = Math.random() * lostedTaskCount / 10
					* ((Math.random() < 0.5) ? -1 : 1);
			lostedTaskCount += delta;
			double y=lostedTaskCount/100;
			if(y>0.99){
				y=0.99;
			}
			dataMass.addData(i, i, y);

		}
		return dataMass;
	}
}
