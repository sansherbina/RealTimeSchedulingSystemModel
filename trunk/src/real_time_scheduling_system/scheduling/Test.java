package real_time_scheduling_system.scheduling;

import java.util.ArrayList;
import real_time_scheduling_system.model.Machine;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.ASchedulingAlgorithm;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.TaskScheduling;

public class Test {
	public static void main(String[] args) {
		ArrayList<Machine> machinesList = new ArrayList<>();
		machinesList.add(new Machine(new MachineConfiguration(0, 10,
				new double[] { 10, 15 }, 0)));
		machinesList.add(new Machine(new MachineConfiguration(1, 10,
				new double[] { 10, 15 }, 1)));
		machinesList.add(new Machine(new MachineConfiguration(2, 10,
				new double[] { 10, 15 }, 2)));

		ArrayList<Task> tasksList = new ArrayList<>();
		tasksList.add(new Task(10d, 10d, 10f, 0.9f, 0));
		tasksList.add(new Task(50d, 50d, 20f, 0.9f, 1));
		tasksList.add(new Task(20d, 21d, 30f, 0.9f, 2));
		tasksList.add(new Task(50d, 5d, 10f, 0.9f, 2));
		tasksList.add(new Task(20d, 26d, 20f, 0.9f, 0));

		ExecutionCostMatrixBuilder executionCostMatrixBuilder = new ExecutionCostMatrixBuilder(
				new TaskOnMachineRelationCalculator());
		ExecutionCostMatrix executionCostMatrix = executionCostMatrixBuilder
				.buildExecutionCostMatrix(tasksList, machinesList);
		System.out.println(executionCostMatrix.toString());
		TaskScheduling randomSched = TaskScheduling
				.generateRandomScheduling(executionCostMatrix);
		System.out.println("Random task=" + randomSched.toString());
		System.out.println("Best="
				+ TaskScheduling.generateTestScheduling(
						executionCostMatrix.getExecutionCostMatrix())
						.getfValue());
		ASchedulingAlgorithm algorithm = new ASchedulingAlgorithm();
		TaskScheduling scheduling = algorithm.scheduleTask(executionCostMatrix);
		System.out.println(scheduling.toString());
	}
}
