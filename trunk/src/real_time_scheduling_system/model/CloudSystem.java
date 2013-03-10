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
	private boolean isSystemOverflowed;

	public CloudSystem(List<MachineConfiguration> machineConfigurations,
			float taskLoadingTimeInterval, float taskLoadingCountBorder,
			IExecutedTaskHandler executedTaskHandler, ITaskFlow taskFlow,
			IExecutionCostMatrixBuilder executionCostMatrixBuilder,
			ISchedulingAlgorithm schedulingAlgorithm) {
		if (machineConfigurations == null || machineConfigurations.size() == 0) {
			throw new IllegalArgumentException();
		}
		this.isSystemOverflowed=false;
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

	public void modelSystem() throws SystemOverflowException {
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
		System.out.println("InputBuffer");
		for(Task task:inputTaskBuffer){
			System.out.println(task.toString());
		}
		// //////
		// /Schedule new tasks if need
		if ((currentTime - lastTaskLoadingTime >= taskLoadingTimeInterval
				|| inputTaskBuffer.size() >= taskLoadingCountBorder) && inputTaskBuffer.size()>0) {
			lastTaskLoadingTime = currentTime;
			ExecutionCostMatrix executionCostMatrix = executionCostMatrixBuilder
					.buildExecutionCostMatrix(inputTaskBuffer, machines);
			int removedCount = 0;
			System.out.println("ExecutionCostMatrix");
			System.out.println(executionCostMatrix.toString());
			List<ExecutingTaskResult> unschedulableTasks = new ArrayList<ExecutingTaskResult>();
			if(executionCostMatrix.getUnschedulableTasks()!=null){
				for (int i = 0; i < executionCostMatrix.getUnschedulableTasks().size(); i++) {
					int taskNumberInInputBuffer = executionCostMatrix
							.getUnschedulableTasks().get(i);
					unschedulableTasks.add(new ExecutingTaskResult(inputTaskBuffer
							.get(taskNumberInInputBuffer-removedCount),
							ExecutingTaskStatus.SCHEDULING_ERROR));
					inputTaskBuffer.remove(taskNumberInInputBuffer - removedCount);
					removedCount++;
				}
			}
			executedTaskHandler.handleExecutedTasks(unschedulableTasks);
			System.out.println("Execution matrix="+executionCostMatrix.toString());
			System.out.println("Start scheduling t="+inputTaskBuffer.size());
			TaskScheduling taskScheduling = schedulingAlgorithm
					.scheduleTask(executionCostMatrix);
			System.out.println("Finish scheduling");
			if (taskScheduling == null) {
				System.out.println("System overflow");
				isSystemOverflowed=true;
				executionCostMatrix=executionCostMatrixBuilder.buildShortRandomizedExecutionCost(inputTaskBuffer, machines, machines.size());
				System.out.println("Start second scheduling");
				taskScheduling=schedulingAlgorithm.scheduleTask(executionCostMatrix);
			}
			if(taskScheduling!=null){
				for (int i = 0; i < taskScheduling.getMachineForTask().length; i++) {
					Task task = inputTaskBuffer.get(executionCostMatrix.getTaskNumbersInInputBuffer()[i]);
					task.setCreationTime(currentTime);
					task.setExecutionTime(0);
					int machineNumber = taskScheduling.getMachineForTask()[i];
					double relation = executionCostMatrix.getExecutionCostMatrix()[i][machineNumber];
					task.setRequestedExecutionTime((float) (task
							.getRequestedExecutionTime() / relation));
					machines.get(machineNumber).addTask(task);
				}
			}
			inputTaskBuffer.clear();
		}
		// //////
	}

	public void modelSystem(float modelingTime) {
		if (modelingTime < MODELING_TIME_INTERVAL) {
			throw new IllegalArgumentException();
		}
		int iterationCount = (int) (modelingTime / MODELING_TIME_INTERVAL);
		for (int i = 0; i < iterationCount; i++) {
			try {
				isSystemOverflowed=false;
				modelSystem();
			} catch (SystemOverflowException e) {
				
			}
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
	
	public int getTaskCountWorkedInSystem(){
		int count=0;
		for(int i=0;i<machines.size();i++){
			System.out.println("Machine id="+machines.get(i).getId()+" taskCount="+machines.get(i).getExecutingTaskCount());
			count+=machines.get(i).getExecutingTaskCount();
		}
		return count;
	}
}
