package real_time_scheduling_system.scheduling.a_scheduling_algorithm;

import java.util.HashMap;
import real_time_scheduling_system.scheduling.IExecutionCostMatrixBuilder;
import real_time_scheduling_system.scheduling.ISchedulingAlgorithm;
import real_time_scheduling_system.scheduling.Machine;
import real_time_scheduling_system.scheduling.Task;

public class ASchedulingAlgorithm implements ISchedulingAlgorithm{
	private IExecutionCostMatrixBuilder executionCostMatrixBuilder;
	public static final int UNSCHEDULED_TASK=-1;
	
	public ASchedulingAlgorithm(
			IExecutionCostMatrixBuilder executionCostMatrixBuilder) {
		super();
		this.executionCostMatrixBuilder = executionCostMatrixBuilder;
	}

	@Override
	public HashMap<Integer, Integer> scheduleTask(Task[] tasks,
			Machine[] machines) throws IllegalArgumentException{
		if(machines==null || machines.length==0){
			throw new IllegalArgumentException();
		}
		if(tasks==null || tasks.length==0){
			return new HashMap<Integer, Integer>();
		}
		Integer[] unschedulableTasks=null;
		float[][] executionCostMatrix=executionCostMatrixBuilder.buildExecutionCostMatrix(tasks, machines, unschedulableTasks);
		TaskScheduling randomGeneratedScheduling=TaskScheduling.generateRandomScheduling(tasks.length, machines.length, executionCostMatrix);
		randomGeneratedScheduling.calculateFunctionF(executionCostMatrix);
		OpenList openList=new OpenList(randomGeneratedScheduling.getfValue());
		openList.insert(new TaskScheduling(tasks.length));
		TaskScheduling resultScheduling=null;
		while(true){
			TaskScheduling taskScheduling=openList.getFirst();
			if(taskScheduling==null){
				resultScheduling=randomGeneratedScheduling;
				break;
			}
			if(taskScheduling.isAllTaskScheduled()){
				resultScheduling=taskScheduling;
				break;
			}
			openList.insert(taskScheduling.generateChildren(executionCostMatrix));
		}
		
		HashMap<Integer, Integer> schedulingById=new HashMap<Integer, Integer>();
		for(int i=0;i<resultScheduling.getMachineForTask().length;i++){
			int machinePositionInArray=resultScheduling.getMachineForTask()[i];
			schedulingById.put(tasks[i].getId(), machines[machinePositionInArray].getId());
		}
		return schedulingById;
	}
	
}
