package real_time_scheduling_system.scheduling;

import java.util.HashMap;

import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.ASchedulingAlgorithm;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.TaskScheduling;

public class Test {
	public static void main(String[] args){
		/*MachineConfiguration[] machines=new MachineConfiguration[]{
				new MachineConfiguration(0, 10, new double[]{10,15}, 0),
				new MachineConfiguration(1, 10, new double[]{10,15}, 1),
				new MachineConfiguration(2, 10, new double[]{10,15}, 2)
		};
		Task[] tasks=new Task[]{
				new Task(0,10d,10d,0,0,10,0),
				new Task(1,50d,50d,0,0,10,1),
				new Task(2,20d,21d,0,0,10,2),
				new Task(3,50d,5d,0,0,10,2),
				new Task(4,20d,26d,0,0,10,1)
		};
		ExecutionCostMatrixBuilder executionCostMatrixBuilder=new ExecutionCostMatrixBuilder();
		ExecutionCostMatrix executionCostMatrix=executionCostMatrixBuilder.buildExecutionCostMatrix(tasks, machines);
		System.out.println(executionCostMatrix.toString());
		TaskScheduling randomSched=TaskScheduling.generateRandomScheduling(tasks.length, machines.length, executionCostMatrix.getExecutionCostMatrix());
		System.out.println("Random task="+randomSched.toString());
		System.out.println("Best="+TaskScheduling.generateTestScheduling(executionCostMatrix.getExecutionCostMatrix()).getfValue());
		ASchedulingAlgorithm algorithm=new ASchedulingAlgorithm(executionCostMatrixBuilder);
		HashMap<Integer, Integer> scheduling=algorithm.scheduleTask(tasks, machines);
		System.out.println(scheduling.toString());*/
	}
}
