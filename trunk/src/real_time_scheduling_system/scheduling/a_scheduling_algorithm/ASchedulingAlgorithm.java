package real_time_scheduling_system.scheduling.a_scheduling_algorithm;

import java.util.HashMap;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;
import real_time_scheduling_system.scheduling.ExecutionCostMatrix;
import real_time_scheduling_system.scheduling.IExecutionCostMatrixBuilder;
import real_time_scheduling_system.scheduling.ISchedulingAlgorithm;

public class ASchedulingAlgorithm implements ISchedulingAlgorithm{
	private IExecutionCostMatrixBuilder executionCostMatrixBuilder;
	public static final int UNSCHEDULED_TASK=-1;
	
	public ASchedulingAlgorithm(
			IExecutionCostMatrixBuilder executionCostMatrixBuilder) {
		super();
		this.executionCostMatrixBuilder = executionCostMatrixBuilder;
	}

	@Override
	public TaskScheduling scheduleTask(ExecutionCostMatrix executionCostMatrix) throws IllegalArgumentException{
		if(executionCostMatrix==null){
			throw new IllegalArgumentException();
		}
		
		TaskScheduling randomGeneratedScheduling=TaskScheduling.generateRandomScheduling(executionCostMatrix.getExecutionCostMatrix());
		OpenList openList=new OpenList(randomGeneratedScheduling.getfValue());
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
			openList.insert(taskScheduling.generateChildren(executionCostMatrix.getExecutionCostMatrix()));
		}
		return resultScheduling;
	}
	
}
