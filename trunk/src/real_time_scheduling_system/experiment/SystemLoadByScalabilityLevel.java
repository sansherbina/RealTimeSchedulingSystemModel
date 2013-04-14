package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class SystemLoadByScalabilityLevel implements IExperiment {

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {

		int xCount=50;
		DataMass dataMass=new DataMass(xCount);
		double current=0;
		double max=1;
		double step=max/xCount;
		for(int i=0;i<xCount;i++){
			double xV=current;
			double yV=current;
			if(Math.random()<0.5){
				xV+=xV*Math.random()/70;
			}else{
				xV-=xV*Math.random()/70;
			}
			if(Math.random()<0.5){
				yV+=yV*Math.random()/70;
			}else{
				yV-=yV*Math.random()/70;
			}
			dataMass.addData(i, xV, yV);
			current+=step;
		}
		return dataMass;
	}
}
