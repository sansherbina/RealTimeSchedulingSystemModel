package real_time_scheduling_system.model;

import java.util.ArrayList;

import real_time_scheduling_system.flow_generators.ITaskFlow;
import real_time_scheduling_system.model.ExecutingTaskResult.ExecutingTaskStatus;
import real_time_scheduling_system.scheduling.ExecutionCostMatrix;
import real_time_scheduling_system.scheduling.IExecutionCostMatrixBuilder;
import real_time_scheduling_system.scheduling.ISchedulingAlgorithm;
import real_time_scheduling_system.scheduling.a_scheduling_algorithm.TaskScheduling;

public class CloudSystem {
	private static final float MODELING_TIME_INTERVAL=1;
	private ArrayList<Machine> machines;
	private ArrayList<Task> executingTasks;
	private float currentTime;
	private ArrayList<Task> inputTaskBuffer;
	private float lastTaskLoadingTime;
	private float taskLoadingTimeInterval;
	private float taskLoadingCountBorder;
	private IExecutedTaskHandler executedTaskHandler;
	private ITaskFlow taskFlow;
	private IExecutionCostMatrixBuilder executionCostMatrixBuilder;
	private ISchedulingAlgorithm schedulingAlgorithm;
	private int maxTaskId;
	
	public CloudSystem(ArrayList<MachineConfiguration> machineConfigurations, 
			float taskLoadingTimeInterval, float taskLoadingCountBorder, IExecutedTaskHandler executedTaskHandler, ITaskFlow taskFlow, IExecutionCostMatrixBuilder executionCostMatrixBuilder, ISchedulingAlgorithm schedulingAlgorithm){
		if(machineConfigurations==null || machineConfigurations.size()==0){
			throw new IllegalArgumentException();
		}
		this.schedulingAlgorithm=schedulingAlgorithm;
		this.maxTaskId=0;
		this.executionCostMatrixBuilder=executionCostMatrixBuilder;
		this.taskFlow=taskFlow;
		this.executedTaskHandler=executedTaskHandler;
		this.taskLoadingCountBorder=taskLoadingCountBorder;
		this.taskLoadingTimeInterval=taskLoadingTimeInterval;
		this.machines=new ArrayList<Machine>();
		this.executingTasks=new ArrayList<Task>();
		this.inputTaskBuffer=new ArrayList<Task>();
		this.currentTime=0;
		for(int i=0;i<machineConfigurations.size();i++){
			machines.add(new Machine(machineConfigurations.get(i)));
		}
	}
	
	private void modelSystem(){
		currentTime+=MODELING_TIME_INTERVAL;
		///Execute tasks
		for(int i=0;i<machines.size();i++){
			machines.get(i).operate(MODELING_TIME_INTERVAL);
		}
		////////
		///Output finished tasks
		ArrayList<ExecutingTaskResult> finishedTasks=new ArrayList<ExecutingTaskResult>();
		for(int i=0;i<machines.size();i++){
			finishedTasks.addAll(machines.get(i).getFinishedTasks());
		}
		executedTaskHandler.handleExecutedTasks(finishedTasks);
		////////
		///Load new tasks from flow
		ArrayList<Task> newInputTasks=taskFlow.generateTasks();
		for(int i=0;i<newInputTasks.size();i++){
			newInputTasks.get(i).setId(maxTaskId);
			maxTaskId++;
		}
		inputTaskBuffer.addAll(newInputTasks);
		////////
		///Schedule new tasks if need
		if(currentTime-lastTaskLoadingTime>=taskLoadingTimeInterval || inputTaskBuffer.size()>=taskLoadingCountBorder){
			lastTaskLoadingTime=currentTime;
			ExecutionCostMatrix executionCostMatrix=executionCostMatrixBuilder.buildExecutionCostMatrix(inputTaskBuffer, machines);
			int removedCount=0;
			ArrayList<ExecutingTaskResult> unschedulableTasks=new ArrayList<ExecutingTaskResult>();
			for(int i=0;i<executionCostMatrix.getUnschedulableTasks().size();i++){
				int taskNumberInInputBuffer=executionCostMatrix.getUnschedulableTasks().get(i);
				unschedulableTasks.add(new ExecutingTaskResult(inputTaskBuffer.get(taskNumberInInputBuffer), ExecutingTaskStatus.SCHEDULING_ERROR));
				inputTaskBuffer.remove(taskNumberInInputBuffer-removedCount);
				removedCount++;
			}
			executedTaskHandler.handleExecutedTasks(unschedulableTasks);
			TaskScheduling taskScheduling=schedulingAlgorithm.scheduleTask(executionCostMatrix);
			for(int i=0;i<taskScheduling.getMachineForTask().length;i++){
				machines.get(taskScheduling.getMachineForTask()[i]).a
			}
		}
		////////
		
	}
	
	public void modelSystem(float modelingTime){
		if(modelingTime<MODELING_TIME_INTERVAL){
			throw new IllegalArgumentException();
		}
		int iterationCount=(int)(modelingTime/MODELING_TIME_INTERVAL);
		for(int i=0;i<iterationCount;i++){
			modelSystem();
		}
	}
}
