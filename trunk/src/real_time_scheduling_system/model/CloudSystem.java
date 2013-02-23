package real_time_scheduling_system.model;

import java.util.ArrayList;
import java.util.List;

import real_time_scheduling_system.flow_generators.ITaskFlow;
import real_time_scheduling_system.model.ExecutingTaskResult.ExecutingTaskStatus;
import real_time_scheduling_system.scheduling.ExecutionCostMatrix;
import real_time_scheduling_system.scheduling.IExecutionCostMatrixBuilder;
import real_time_scheduling_system.scheduling.ISchedulingAlgorithm;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.TaskScheduling;

public class CloudSystem {
	private static final float MODELING_TIME_INTERVAL = 1;
	private ArrayList<Machine> machines;
	private float currentTime;
	private List<Task> inputTaskBuffer;
	private float lastTaskLoadingTime;
	private float taskLoadingTimeInterval;
	private float taskLoadingCountBorder;
	private IExecutedTaskHandler executedTaskHandler;
	private ITaskFlow taskFlow;
	private IExecutionCostMatrixBuilder executionCostMatrixBuilder;
	private ISchedulingAlgorithm schedulingAlgorithm;
	private int maxTaskId;

	public CloudSystem(List<MachineConfiguration> machineConfigurations,
			float taskLoadingTimeInterval, float taskLoadingCountBorder,
			IExecutedTaskHandler executedTaskHandler, ITaskFlow taskFlow,
			IExecutionCostMatrixBuilder executionCostMatrixBuilder,
			ISchedulingAlgorithm schedulingAlgorithm) {
		if (machineConfigurations == null || machineConfigurations.size() == 0) {
			throw new IllegalArgumentException();
		}
		this.schedulingAlgorithm = schedulingAlgorithm;
		this.maxTaskId = 0;
		this.executionCostMatrixBuilder = executionCostMatrixBuilder;
		this.taskFlow = taskFlow;
		this.executedTaskHandler = executedTaskHandler;
		this.taskLoadingCountBorder = taskLoadingCountBorder;
		this.taskLoadingTimeInterval = taskLoadingTimeInterval;
		this.machines = new ArrayList<Machine>();
		this.inputTaskBuffer = new ArrayList<Task>();
		this.currentTime = 0;
		for (int i = 0; i < machineConfigurations.size(); i++) {
			machines.add(new Machine(machineConfigurations.get(i)));
		}
	}

	private void modelSystem() {
		currentTime += MODELING_TIME_INTERVAL;
		// /Execute tasks
		for (int i = 0; i < machines.size(); i++) {
			machines.get(i).operate(MODELING_TIME_INTERVAL);
		}
		// //////
		// /Output finished tasks
		List<ExecutingTaskResult> finishedTasks = new ArrayList<ExecutingTaskResult>();
		for (int i = 0; i < machines.size(); i++) {
			finishedTasks.addAll(machines.get(i).getFinishedTasks());
		}
		executedTaskHandler.handleExecutedTasks(finishedTasks);
		// //////
		// /Load new tasks from flow
		List<Task> newInputTasks = taskFlow
				.generateTasks(MODELING_TIME_INTERVAL);
		for (int i = 0; i < newInputTasks.size(); i++) {
			newInputTasks.get(i).setId(maxTaskId);
			maxTaskId++;
		}
		inputTaskBuffer.addAll(newInputTasks);
		// //////
		// /Schedule new tasks if need
		if (currentTime - lastTaskLoadingTime >= taskLoadingTimeInterval
				|| inputTaskBuffer.size() >= taskLoadingCountBorder) {
			lastTaskLoadingTime = currentTime;
			ExecutionCostMatrix executionCostMatrix = executionCostMatrixBuilder
					.buildExecutionCostMatrix(inputTaskBuffer, machines);
			int removedCount = 0;
			List<ExecutingTaskResult> unschedulableTasks = new ArrayList<ExecutingTaskResult>();
			for (int i = 0; i < executionCostMatrix.getUnschedulableTasks()
					.size(); i++) {
				int taskNumberInInputBuffer = executionCostMatrix
						.getUnschedulableTasks().get(i);
				unschedulableTasks.add(new ExecutingTaskResult(inputTaskBuffer
						.get(taskNumberInInputBuffer),
						ExecutingTaskStatus.SCHEDULING_ERROR));
				inputTaskBuffer.remove(taskNumberInInputBuffer - removedCount);
				removedCount++;
			}
			executedTaskHandler.handleExecutedTasks(unschedulableTasks);
			TaskScheduling taskScheduling = schedulingAlgorithm
					.scheduleTask(executionCostMatrix);
			if (taskScheduling == null) {
				System.out.println("System overflow");
			}
			for (int i = 0; i < taskScheduling.getMachineForTask().length; i++) {
				Task task = inputTaskBuffer.get(i);
				task.setCreationTime(currentTime);
				task.setExecutionTime(0);
				int machineNumber = taskScheduling.getMachineForTask()[i];
				double relation = executionCostMatrix.getExecutionCostMatrix()[i][machineNumber];
				task.setRequestedExecutionTime((float) (task
						.getRequestedExecutionTime() / relation));
				machines.get(machineNumber).addTask(task);
			}
		}
		// //////
	}

	public void modelSystem(float modelingTime) {
		if (modelingTime < MODELING_TIME_INTERVAL) {
			throw new IllegalArgumentException();
		}
		int iterationCount = (int) (modelingTime / MODELING_TIME_INTERVAL);
		for (int i = 0; i < iterationCount; i++) {
			modelSystem();
		}
	}

	public int getInputTaskBufferSize() {
		return this.inputTaskBuffer.size();
	}

	public double getAvverageMachinesWorkLoad() {
		double machinesWorkLoad = 0;
		for (int i = 0; i < machines.size(); i++) {
			machinesWorkLoad += machines.get(i).getWorkTime() / currentTime;
		}
		return machinesWorkLoad / machines.size();
	}
}
