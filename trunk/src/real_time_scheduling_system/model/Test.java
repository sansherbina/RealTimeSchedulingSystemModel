package real_time_scheduling_system.model;

import java.util.ArrayList;

import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.flow_generators.ITaskFlow;
import real_time_scheduling_system.flow_generators.TaskFlowBuilder;
import real_time_scheduling_system.scheduling.ExecutionCostMatrixBuilder;
import real_time_scheduling_system.scheduling.TaskOnMachineRelationCalculator;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.ASchedulingAlgorithm;

public class Test {
	public static void main(String[] args) {
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
		
		ExecutedTasksHandler executedTaskHandler = new ExecutedTasksHandler();
		ITaskFlow taskFlow = TaskFlowBuilder.buildTaskFlow(modelSettings);
		CloudSystem cloudSystem = new CloudSystem(machineConfigurations,
				modelSettings.getTaskLoadingTimeInterval(),
				modelSettings.getTaskLoadingCountBorder(), executedTaskHandler,
				taskFlow, new ExecutionCostMatrixBuilder(
						new TaskOnMachineRelationCalculator()),
				new ASchedulingAlgorithm());
		/*for(int i=0;i<50;i++){
			System.out.println("***********************i="+i+"***************");
			try {
				cloudSystem.modelSystem();
			} catch (SystemOverflowException e) {
				System.out.println("System overflow");
				e.printStackTrace();
			}
			if(i%10==0){
				System.out.println("Avvegare workLoad="+cloudSystem.getAvverageMachinesWorkLoad());
				System.out.println("TaskCountInSystem="+cloudSystem.getTaskCountWorkedInSystem());
				System.out.println("InputBufferSize="+cloudSystem.getInputTaskBufferSize());
				System.out.println("SuccExec="+executedTaskHandler.getSuccesfullyExecutedTasksCount());
				System.out.println("FailExec="+executedTaskHandler.getExecutionErrorsCount());
				System.out.println("Unsched="+executedTaskHandler.getUnscheduledTasksCount());
			}
		}*/
		cloudSystem.modelSystem(1000);
		System.out.println("Avvegare workLoad="+cloudSystem.getAvverageMachinesWorkLoad());
		System.out.println("TaskCountInSystem="+cloudSystem.getTaskCountWorkedInSystem());
		System.out.println("InputBufferSize="+cloudSystem.getInputTaskBufferSize());
		System.out.println("SuccExec="+executedTaskHandler.getSuccesfullyExecutedTasksCount());
		System.out.println("FailExec="+executedTaskHandler.getExecutionErrorsCount());
		System.out.println("Unsched="+executedTaskHandler.getUnscheduledTasksCount());
	}
}
