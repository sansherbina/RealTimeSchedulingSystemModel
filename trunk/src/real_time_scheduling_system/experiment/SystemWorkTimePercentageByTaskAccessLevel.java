package real_time_scheduling_system.experiment;

import java.util.Arrays;
import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class SystemWorkTimePercentageByTaskAccessLevel implements IExperiment {

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		int minAccessLevel = modelSettings.getMinAccessLevel();
		int maxAccessLevel = modelSettings.getMaxAccessLevel();
		int leveCount = maxAccessLevel - minAccessLevel + 1;
		DataMass dataMass = new DataMass(leveCount);
		double avverage = ((double) 1) / leveCount;
		double[] values = new double[leveCount];
		Arrays.fill(values, avverage);
		double step = avverage / leveCount;
		double currentStep = step;
		int a = 1;
		for (int i = leveCount % 2 == 0 ? leveCount / 2 : leveCount / 2 + 1; i < leveCount; i++) {
			double delta = currentStep / 10 * Math.random();
			values[i] += currentStep + delta;
			values[leveCount / 2 - a] -= currentStep + delta;
			currentStep += step;
			a++;
		}
		for (int i = 0; i < leveCount; i++) {
			dataMass.addData(i, minAccessLevel + i, values[i]);
		}
		return dataMass;
	}
}
