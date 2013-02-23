package real_time_scheduling_system.scheduling.a_scheduling_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import real_time_scheduling_system.scheduling.ExecutionCostMatrix;
import real_time_scheduling_system.scheduling.IExecutionCostMatrixBuilder;

public class TaskScheduling {
	private int[] machineForTask;
	private float fValue;

	public static TaskScheduling generateTestScheduling(float[][] executionCost) {
		TaskScheduling taskScheduling = new TaskScheduling(5);
		taskScheduling.machineForTask[0] = 1;
		taskScheduling.machineForTask[1] = 0;
		taskScheduling.machineForTask[2] = 2;
		taskScheduling.machineForTask[3] = 2;
		taskScheduling.machineForTask[4] = 1;
		taskScheduling.calculateFunctionF(executionCost);
		return taskScheduling;
	}

	public boolean isSchedulingOverflowSystem(
			ExecutionCostMatrix executionCostMatrix) {
		double[] currentMachinesLoading = new double[executionCostMatrix
				.getMachinesLoading().length];
		System.arraycopy(executionCostMatrix.getMachinesLoading(), 0,
				currentMachinesLoading, 0, currentMachinesLoading.length);
		for (int i = 0; i < machineForTask.length; i++) {
			if (machineForTask[i] == ASchedulingAlgorithm.UNSCHEDULED_TASK) {
				return false;
			}
			int machineNumber = machineForTask[i];
			currentMachinesLoading[machineNumber] += executionCostMatrix
					.getWorkTimePercentageForTask()[i]
					/ executionCostMatrix.getExecutionCostMatrix()[i][machineNumber];
			if (currentMachinesLoading[machineNumber] > 1) {
				return true;
			}
		}
		return false;
	}

	public static TaskScheduling generateRandomScheduling(
			ExecutionCostMatrix executionCostMatrix) {
		TaskScheduling taskScheduling = new TaskScheduling(
				executionCostMatrix.getExecutionCostMatrix().length);
		for (int i = 0; i < executionCostMatrix.getExecutionCostMatrix().length; i++) {
			int machineNumber = i
					% executionCostMatrix.getExecutionCostMatrix()[0].length;
			if (executionCostMatrix.getExecutionCostMatrix()[i][machineNumber] == IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION) {
				int availableMachinesCount = 0;
				for (int j = 0; j < executionCostMatrix
						.getExecutionCostMatrix()[i].length; j++) {
					if (executionCostMatrix.getExecutionCostMatrix()[i][j] != IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION) {
						availableMachinesCount++;
					}
				}
				machineNumber = (int) (Math.random() * availableMachinesCount);
				int currentPosition = 0;
				for (int j = 0; j < executionCostMatrix
						.getExecutionCostMatrix()[i].length; j++) {
					if (executionCostMatrix.getExecutionCostMatrix()[i][j] != IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION) {
						if (currentPosition == machineNumber) {
							machineNumber = j;
							break;
						}
						currentPosition++;
					}
				}
			}
			taskScheduling.machineForTask[i] = machineNumber;
		}
		taskScheduling.calculateFunctionF(executionCostMatrix
				.getExecutionCostMatrix());
		return taskScheduling;
	}

	protected TaskScheduling(int taskCount) {
		machineForTask = new int[taskCount];
		Arrays.fill(machineForTask, ASchedulingAlgorithm.UNSCHEDULED_TASK);
		fValue = 0;
	}

	private TaskScheduling(TaskScheduling source) {
		if (source == null) {
			throw new IllegalArgumentException();
		}
		this.machineForTask = new int[source.machineForTask.length];
		System.arraycopy(source.machineForTask, 0, machineForTask, 0,
				source.machineForTask.length);
	}

	protected void calculateFunctionF(float[][] executionCost) {
		float[] machinesLoading = new float[executionCost[0].length];
		for (int i = 0; i < machineForTask.length; i++) {
			if (machineForTask[i] != ASchedulingAlgorithm.UNSCHEDULED_TASK) {
				int machineNumber = machineForTask[i];
				machinesLoading[machineNumber] += executionCost[i][machineNumber];
			}
		}
		float maxLoading = 0;
		for (int i = 0; i < machinesLoading.length; i++) {
			if (maxLoading < machinesLoading[i]) {
				maxLoading = machinesLoading[i];
			}
		}
		fValue = maxLoading;
	}

	public float getfValue() {
		return fValue;
	}

	protected List<TaskScheduling> generateChildren(
			ExecutionCostMatrix executionCostMatrix) {
		for (int i = 0; i < machineForTask.length; i++) {
			if (machineForTask[i] == ASchedulingAlgorithm.UNSCHEDULED_TASK) {
				List<TaskScheduling> children = new ArrayList<TaskScheduling>();
				for (int j = 0; j < executionCostMatrix
						.getExecutionCostMatrix()[0].length; j++) {
					if (executionCostMatrix.getExecutionCostMatrix()[i][j] != IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION) {
						TaskScheduling child = new TaskScheduling(this);
						child.machineForTask[i] = j;
						if (!child
								.isSchedulingOverflowSystem(executionCostMatrix)) {
							child.calculateFunctionF(executionCostMatrix
									.getExecutionCostMatrix());
							children.add(child);
						}
					}
				}
				return children;
			}
		}
		return null;
	}

	protected boolean isAllTaskScheduled() {
		for (int i = 0; i < machineForTask.length; i++) {
			if (machineForTask[i] == ASchedulingAlgorithm.UNSCHEDULED_TASK) {
				return false;
			}
		}
		return true;
	}

	public int[] getMachineForTask() {
		return machineForTask;
	}

	public String toString() {
		String result = "Machine for task=" + Arrays.toString(machineForTask)
				+ " fValue=" + fValue;
		return result;
	}
}
