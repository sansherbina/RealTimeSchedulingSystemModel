package real_time_scheduling_system.scheduling.a_scheduling_algorithm;

import java.util.ArrayList;
import java.util.Arrays;

import real_time_scheduling_system.scheduling.IExecutionCostMatrixBuilder;

public class TaskScheduling{
	private int[] machineForTask;
	private float fValue;
	
	public static TaskScheduling generateTestScheduling(float[][] executionCost){
		TaskScheduling taskScheduling=new TaskScheduling(5);
		taskScheduling.machineForTask[0]=1;
		taskScheduling.machineForTask[1]=0;
		taskScheduling.machineForTask[2]=2;
		taskScheduling.machineForTask[3]=2;
		taskScheduling.machineForTask[4]=1;
		taskScheduling.calculateFunctionF(executionCost);
		return taskScheduling;
	}
	
	public static TaskScheduling generateRandomScheduling(int taskCount, int machinesCount, float[][] executionCost){
		TaskScheduling taskScheduling=new TaskScheduling(taskCount);
		for(int i=0;i<taskCount;i++){
			int machineNumber=i%machinesCount;
			if(executionCost[i][machineNumber]==IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION){
				int availableMachinesCount=0;
				for(int j=0;j<executionCost[i].length;j++){
					if(executionCost[i][j]!=IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION){
						availableMachinesCount++;
					}
				}
				machineNumber=(int)(Math.random()*availableMachinesCount);
				int currentPosition=0;
				for(int j=0;j<executionCost[i].length;j++){
					if(executionCost[i][j]!=IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION){
						if(currentPosition==machineNumber){
							machineNumber=j;
							break;
						}
						currentPosition++;
					}
				}
			}
			taskScheduling.machineForTask[i]=machineNumber;
		}
		taskScheduling.calculateFunctionF(executionCost);
		return taskScheduling;
	}
	
	public TaskScheduling(int taskCount){
		machineForTask=new int[taskCount];
		Arrays.fill(machineForTask, ASchedulingAlgorithm.UNSCHEDULED_TASK);
		fValue=0;
	}
	
	private TaskScheduling(TaskScheduling source){
		if(source==null){
			throw new IllegalArgumentException();
		}
		this.machineForTask=new int[source.machineForTask.length];
		System.arraycopy(source.machineForTask, 0, machineForTask, 0, source.machineForTask.length);
	}
	
	public void calculateFunctionF(float[][] executionCost){
		float[] machinesLoading=new float[executionCost[0].length];
		for(int i=0;i<machineForTask.length;i++){
			if(machineForTask[i]!=ASchedulingAlgorithm.UNSCHEDULED_TASK){
				int machineNumber=machineForTask[i];
				machinesLoading[machineNumber]+=executionCost[i][machineNumber];
			}
		}
		float maxLoading=0;
		for(int i=0;i<machinesLoading.length;i++){
			if(maxLoading<machinesLoading[i]){
				maxLoading=machinesLoading[i];
			}
		}
		fValue=maxLoading;
	}

	public float getfValue() {
		return fValue;
	}
	
	public ArrayList<TaskScheduling> generateChildren(float[][] executionCost){
		for(int i=0;i<machineForTask.length;i++){
			if(machineForTask[i]==ASchedulingAlgorithm.UNSCHEDULED_TASK){
				ArrayList<TaskScheduling> children=new ArrayList<TaskScheduling>();
				for(int j=0;j<executionCost[0].length;j++){
					if(executionCost[i][j]!=IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION){
						TaskScheduling child=new TaskScheduling(this);
						child.machineForTask[i]=j;
						child.calculateFunctionF(executionCost);
						children.add(child);
					}
				}
				return children;
			}
		}
		return null;
	}
	
	public boolean isAllTaskScheduled(){
		for(int i=0;i<machineForTask.length;i++){
			if(machineForTask[i]==ASchedulingAlgorithm.UNSCHEDULED_TASK){
				return false;
			}
		}
		return true;
	}

	public int[] getMachineForTask() {
		return machineForTask;
	}
	
	public String toString(){
		String result="Machine for task="+Arrays.toString(machineForTask)+" fValue="+fValue;
		return result;
	}
}
