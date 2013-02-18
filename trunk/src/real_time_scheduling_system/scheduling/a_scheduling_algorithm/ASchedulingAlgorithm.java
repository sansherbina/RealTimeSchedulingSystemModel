package real_time_scheduling_system.scheduling.a_scheduling_algorithm;

import real_time_scheduling_system.scheduling.ExecutionCostMatrix;
import real_time_scheduling_system.scheduling.ISchedulingAlgorithm;

public class ASchedulingAlgorithm implements ISchedulingAlgorithm{
	public static final int UNSCHEDULED_TASK=-1;
	public static final int ITEM_COUNT_FOR_GENERATING_RANDOM_SCHEDULING=5;
	public ASchedulingAlgorithm() {
	}

	@Override
	public TaskScheduling scheduleTask(ExecutionCostMatrix executionCostMatrix) throws IllegalArgumentException{
		if(executionCostMatrix==null){
			throw new IllegalArgumentException();
		}
		
		TaskScheduling randomGeneratedScheduling=null;
		for(int i=0;i<ITEM_COUNT_FOR_GENERATING_RANDOM_SCHEDULING;i++){
			randomGeneratedScheduling=TaskScheduling.generateRandomScheduling(executionCostMatrix);
			if(!randomGeneratedScheduling.isSchedulingOverflowSystem(executionCostMatrix)){
				break;
			}else{
				randomGeneratedScheduling=null;
			}
		}
		float fValueForRandomScheduling=Float.MAX_VALUE;
		if(randomGeneratedScheduling!=null){
			fValueForRandomScheduling=randomGeneratedScheduling.getfValue();
		}
		OpenList openList=new OpenList(fValueForRandomScheduling);
		openList.insert(new TaskScheduling(executionCostMatrix.getExecutionCostMatrix().length));
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
		return resultScheduling;
	}
	
}
