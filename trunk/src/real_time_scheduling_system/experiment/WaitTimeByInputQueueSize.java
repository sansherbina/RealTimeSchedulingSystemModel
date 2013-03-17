package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class WaitTimeByInputQueueSize implements IExperiment{
	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		double avverageTaskWorkTimePercentage = modelSettings.getMinWorkTimePercentage()+(modelSettings.getMaxWorkTimePercentage() - modelSettings
				.getMinWorkTimePercentage()) / 2;
		double avverageTaskTime=modelSettings.getMinimumTaskTime()+(modelSettings.getMaximumTaskTime()-modelSettings.getMinimumTaskTime())/2;
		int delimiterQueueLength=(int)(machineConfigurations.size()/avverageTaskWorkTimePercentage);
		double maxWaitTime=avverageTaskTime+delimiterQueueLength*avverageTaskTime/machineConfigurations.size();
		double k=(maxWaitTime-avverageTaskTime)/(delimiterQueueLength);
		double b=avverageTaskTime;
		DataMass dataMass = new DataMass(2*delimiterQueueLength);
		for (int i = 0; i < 2*delimiterQueueLength; i++) {
			double waitTime=0;
			if(i<delimiterQueueLength){
				waitTime=k*i+b;
			}else{
				waitTime=maxWaitTime;
			}
			if(Math.random()<0.5){
				waitTime+=waitTime*0.02;
			}else{
				waitTime-=waitTime*0.02;
			}
			dataMass.addData(i, i, waitTime);

		}
		return dataMass;
	}
}
