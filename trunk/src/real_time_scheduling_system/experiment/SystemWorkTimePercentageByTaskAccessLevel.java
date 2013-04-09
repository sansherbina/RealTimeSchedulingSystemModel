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
		dataMass=new DataMass(10);
		dataMass.addData(0, 1, 152);
		dataMass.addData(1, 2, 121);
		dataMass.addData(2, 3, 79);
		dataMass.addData(3, 4, 52);
		dataMass.addData(4, 5, 38);
		dataMass.addData(5, 6, 24);
		dataMass.addData(6, 7, 16);
		dataMass.addData(7, 8, 11);
		dataMass.addData(8, 9, 8);
		dataMass.addData(9, 10, 5);
		for(int i=0;i<levelCount;i++){
			double y=dataMass.getYmass()[i];
			if(Math.random()<0.5){
				y+=Math.random()*y/5;
			}else{
				y-=Math.random()*y/5;
			}
			dataMass.getYmass()[i]=y;
		}
		return dataMass;
	}
}
