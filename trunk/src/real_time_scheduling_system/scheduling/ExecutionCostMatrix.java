package real_time_scheduling_system.scheduling;

import java.util.ArrayList;
import java.util.Arrays;

public class ExecutionCostMatrix {
	private float[][] executionCostMatrix;
	private double[] machinesLoading;
	private double[] workTimePercentageForTask;
	private ArrayList<Integer> unschedulableTasks;
	
	public ExecutionCostMatrix(float[][] executionCostMatrix, double[] machinesLoading, double[] workTimePercentageForTask,
			ArrayList<Integer> unschedulableTasks) {
		super();
		this.workTimePercentageForTask=workTimePercentageForTask;
		this.executionCostMatrix = executionCostMatrix;
		this.unschedulableTasks = unschedulableTasks;
		this.machinesLoading=machinesLoading;
	}
	
	public float[][] getExecutionCostMatrix() {
		return executionCostMatrix;
	}
	
	public ArrayList<Integer> getUnschedulableTasks() {
		return unschedulableTasks;
	}
	
	public double[] getMachinesLoading() {
		return machinesLoading;
	}

	public double[] getWorkTimePercentageForTask() {
		return workTimePercentageForTask;
	}

	public String toString(){
		String result="Execution Cost Matrix"+'\n';
		for(int i=0;i<executionCostMatrix.length;i++){
			result+=Arrays.toString(executionCostMatrix[i])+'\n';
		}
		result+="Unschedulable tasks=";
		if(unschedulableTasks==null){
			result+="empty";
		}else{
			result+=unschedulableTasks.toString();
		}
		return result;
	}
}
