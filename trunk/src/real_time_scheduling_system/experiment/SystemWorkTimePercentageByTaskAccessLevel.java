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
		int levelCount = maxAccessLevel - minAccessLevel + 1;
		DataMass dataMass = new DataMass(levelCount);
		double startValue=55;
		double endValue=15;
		double step=(startValue-endValue)/levelCount;
		double currentValue=startValue;
		for(int i=0;i<levelCount;i++){
			double y=currentValue;
			currentValue-=step;
			if(Math.random()<0.5){
				y+=Math.random()*y/10;
			}else{
				y-=Math.random()*y/10;
			}
			dataMass.addData(i, i+1, y);
		}
		return dataMass;
	}
}
