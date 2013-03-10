package real_time_scheduling_system.experiment;

import java.util.ArrayList;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;


public class Test {
	public static void main(String args[]){
		ArrayList<MachineConfiguration> machineConfigurations = new ArrayList<>();
		machineConfigurations.add(new MachineConfiguration(0, 1000,
				new double[] { 2.1, 2.1 }, 1));
		machineConfigurations.add(new MachineConfiguration(1, 2000,
				new double[] { 4.1, 4.1 }, 2));
		machineConfigurations.add(new MachineConfiguration(2, 3000,
				new double[] { 3.1, 3.1 }, 3));
		machineConfigurations.add(new MachineConfiguration(3, 4000,
				new double[] { 2.2, 2.2 }, 4));
		machineConfigurations.add(new MachineConfiguration(4, 2000,
				new double[] { 2.4, 2.4, 2.4 }, 8));
		machineConfigurations.add(new MachineConfiguration(5, 2000,
				new double[] { 2.1, 2.1, 2.1 }, 9));
		machineConfigurations.add(new MachineConfiguration(6, 3000,
				new double[] { 3.1, 3.1, 3.1 }, 9));
		machineConfigurations.add(new MachineConfiguration(7, 4000,
				new double[] { 2.1, 2.1 }, 8));
		
		ModelSettings modelSettings = new ModelSettings(1, 10, 2, 5f, 5f, 1,
				1000f, 0.5d, 0.3d, 1d, 4d, 1000d, 4000d, 0.1f, 0.8f, 0.5d, 4d,
				1, 10);
		
		IExperiment experiment=new SystemLoadByInputQueueLengthExperiment();
		DataMass dataMass=experiment.makeExperiment(modelSettings, machineConfigurations);
		System.out.println(dataMass.toString());
	}
}
