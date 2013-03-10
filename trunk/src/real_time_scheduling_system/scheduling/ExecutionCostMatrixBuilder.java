package real_time_scheduling_system.scheduling;

import java.util.ArrayList;
import java.util.List;

import real_time_scheduling_system.model.Machine;
import real_time_scheduling_system.model.Task;

public class ExecutionCostMatrixBuilder implements IExecutionCostMatrixBuilder {
	private ITaskOnMachineRelationCalculator taskOnMachineRelationCalculator;

	public ExecutionCostMatrixBuilder(
			ITaskOnMachineRelationCalculator taskOnMachineRelationCalculator) {
		if (taskOnMachineRelationCalculator == null) {
			throw new IllegalArgumentException();
		}
		this.taskOnMachineRelationCalculator = taskOnMachineRelationCalculator;
	}

	@Override
	public ExecutionCostMatrix buildExecutionCostMatrix(List<Task> tasks,
			List<Machine> machines, int[] taskNumberInInputBuffer) {
		if (tasks == null || machines == null || machines.size() == 0
				|| tasks.size() == 0) {
			System.out.println("MC="+machines.size());
			System.out.println("TC="+tasks.size());
			throw new IllegalArgumentException();
		}

		float[][] executionCost = new float[tasks.size()][];
		for (int i = 0; i < executionCost.length; i++) {
			executionCost[i] = new float[machines.size()];
			for (int j = 0; j < executionCost[i].length; j++) {
				if (machines.get(j).getAccessLevel() >= tasks.get(i)
						.getAccessLevel()) {
					double relation = taskOnMachineRelationCalculator
							.calculateTaskMachineRelation(tasks.get(i),
									machines.get(j));
					executionCost[i][j] = (float) (tasks.get(i)
							.getRequestedExecutionTime() / relation);
				} else {
					executionCost[i][j] = IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION;
				}
			}
		}
		List<Integer> unschedulableTasksList = new ArrayList<Integer>();
		for (int i = 0; i < executionCost.length; i++) {
			boolean isSchedulable = false;
			for (int j = 0; j < executionCost[i].length; j++) {
				if (executionCost[i][j] != IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION) {
					isSchedulable = true;
					break;
				}
			}
			if (!isSchedulable) {
				unschedulableTasksList.add(i);
			}
		}

		double[] machinesLoading = new double[machines.size()];
		for (int i = 0; i < machinesLoading.length; i++) {
			machinesLoading[i] = machines.get(i).calculateLoading();
		}

		double[] workTimePercentageForTask = new double[tasks.size()];
		for (int i = 0; i < workTimePercentageForTask.length; i++) {
			workTimePercentageForTask[i] = tasks.get(i).getWorkTimePercentage();
		}

		if (unschedulableTasksList.size() == 0) {
			return new ExecutionCostMatrix(executionCost, machinesLoading,
					workTimePercentageForTask, null, taskNumberInInputBuffer);
		}
		float[][] executionCostWithoutUnschedulableTasks = new float[executionCost.length
				- unschedulableTasksList.size()][];
		int currentTask = 0;
		for (int i = 0; i < executionCost.length; i++) {
			if (!unschedulableTasksList.contains(i)) {
				executionCostWithoutUnschedulableTasks[currentTask] = executionCost[i];
				currentTask++;
			}
		}
		return new ExecutionCostMatrix(executionCostWithoutUnschedulableTasks,
				machinesLoading, workTimePercentageForTask,
				unschedulableTasksList, taskNumberInInputBuffer);
	}
	
	public ExecutionCostMatrix buildShortRandomizedExecutionCost(List<Task> tasks,
			List<Machine> machines, int taskCount){
		List<Task> randomTakedTasks=new ArrayList<>();
		List<Task> tasksCopy=new ArrayList<>();
		tasksCopy.addAll(tasks);
		int[] taskNumbersInInputBuffer=null;
		if(tasksCopy.size()<=taskCount){
			randomTakedTasks.addAll(tasksCopy);
			taskNumbersInInputBuffer=new int[tasksCopy.size()];
			for(int i=0;i<taskNumbersInInputBuffer.length;i++){
				taskNumbersInInputBuffer[i]=i;
			}
		}else{
			taskNumbersInInputBuffer=new int[taskCount];
			for(int i=0;i<taskCount;i++){
				int taskNumber=(int)(Math.random()*tasksCopy.size());
				randomTakedTasks.add(tasksCopy.get(taskNumber));
				taskNumbersInInputBuffer[i]=tasks.indexOf(tasksCopy.get(taskNumber));
				tasksCopy.remove(taskNumber);
			}
		}
		return buildExecutionCostMatrix(randomTakedTasks, machines, taskNumbersInInputBuffer);
	}

	@Override
	public ExecutionCostMatrix buildExecutionCostMatrix(List<Task> tasks,
			List<Machine> machines) {
		if (tasks == null || machines == null || machines.size() == 0
				|| tasks.size() == 0) {
			System.out.println("MC="+machines.size());
			System.out.println("TC="+tasks.size());
			throw new IllegalArgumentException();
		}
		int[] tasksNumberInInputBuffer=new int[tasks.size()];
		for(int i=0;i<tasksNumberInInputBuffer.length;i++){
			tasksNumberInInputBuffer[i]=i;
		}
		return buildExecutionCostMatrix(tasks, machines, tasksNumberInInputBuffer);
	}
}
