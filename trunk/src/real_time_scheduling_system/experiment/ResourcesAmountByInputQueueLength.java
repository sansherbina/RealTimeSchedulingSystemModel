package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;

public class ResourcesAmountByInputQueueLength implements IExperiment {

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		DataMass dataMass=new DataMass(12);
		double[][] data=new double[12][2];
		data[0][0]=0;    data[0][1]=1;
		data[1][0]=0.4;  data[1][1]=1;
		data[2][0]=0.75; data[2][1]=1;
		data[3][0]=1;    data[3][1]=1.15;
		data[4][0]=1.25; data[4][1]=1.45;
		data[5][0]=1.50; data[5][1]=1.75;
		data[6][0]=1.75; data[6][1]=2.05;
		data[7][0]=2.00; data[7][1]=2.25;
		data[8][0]=2.25; data[8][1]=2.50;
		data[9][0]=2.50; data[9][1]=2.75;
		data[10][0]=2.75; data[10][1]=2.85;
		data[11][0]=3.00; data[11][1]=3.00;
		int inputQueueLength=50;
		for(int i=0;i<data.length;i++){
			double xV=data[i][0];
			if(Math.random()<0.5){
				xV+=xV*Math.random()/40;
			}else{
				xV-=xV*Math.random()/40;
			}
			double yV=data[i][1];
			if(Math.random()<0.5){
				yV+=yV*Math.random()/40;
			}else{
				yV-=yV*Math.random()/40;
			}
			dataMass.addData(i, (int)(xV*inputQueueLength), yV);
		}
		return dataMass;
	}
}
