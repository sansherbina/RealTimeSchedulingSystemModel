package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class FailProbabilityByScalabilityLevel implements IExperiment {

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		int xCount = 100;
		DataMass dataMass = new DataMass(xCount);
		double y1=0;
		double y2=0.99;
		for(int i=0;i<xCount*0.75;i++){
			double xValue=((double)i)/100;
			double yValue=0.02;
			if(Math.random()>0.5){
				yValue+=yValue*Math.random()/50;
			}else{
				yValue-=yValue*Math.random()/50;
			}
			dataMass.addData(i, xValue, yValue);
			y1=yValue;
		}
		double x1=xCount*0.75;
		double x2=xCount;
		double b=Math.log(y1/y2)/(x1-x2);
		double a=y1/Math.exp(b*x1);
		for(int i=(int)(xCount*0.75);i<xCount;i++){
			double yValue=a*Math.exp(b*i);
			if(Math.random()>0.5){
				yValue+=yValue*Math.random()/10;
			}else{
				yValue-=yValue*Math.random()/10;
			}
			dataMass.addData(i, ((double)i/100), yValue);
		}
		return dataMass;
	}
}
