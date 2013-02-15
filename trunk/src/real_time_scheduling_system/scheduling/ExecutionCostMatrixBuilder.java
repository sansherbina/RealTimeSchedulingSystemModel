package real_time_scheduling_system.scheduling;

import java.util.ArrayList;

import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.model.Task;

public class ExecutionCostMatrixBuilder implements IExecutionCostMatrixBuilder{

	@Override
	public ExecutionCostMatrix buildExecutionCostMatrix(Task[] tasks, MachineConfiguration[] machines) {
		if(tasks==null || machines==null || machines.length==0 || tasks.length==0){
			throw new IllegalArgumentException();
		}
		
		float[][] executionCost=new float[tasks.length][];
		double[] machinesProcessors=new double[machines.length];
		for(int i=0;i<machines.length;i++){
			for(int j=0;j<machines[i].getProcessors().length;j++){
				machinesProcessors[i]+=machines[i].getProcessors()[j];
			}
		}
		for(int i=0;i<executionCost.length;i++){
			executionCost[i]=new float[machines.length];
			for(int j=0;j<executionCost[i].length;j++){
				if(machines[j].getAccessLevel()>=tasks[i].getAccessLevel()){
					double memmoryRelation=machines[j].getMemmoryCapacity()/tasks[i].getMemmoryCapactity();
					double machineRelation=machinesProcessors[j]/tasks[i].getProcessor();
					double relation=(memmoryRelation+machineRelation)/2;
					executionCost[i][j]=(float)(tasks[i].getRequestedExecutionTime()/relation);
				}else{
					executionCost[i][j]=IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION;
				}
			}
		}
		ArrayList<Integer> unschedulableTasksList=new ArrayList<Integer>();
		for(int i=0;i<executionCost.length;i++){
			boolean isSchedulable=false;
			for(int j=0;j<executionCost[i].length;j++){
				if(executionCost[i][j]!=IExecutionCostMatrixBuilder.PROHIBITED_EXECUTION){
					isSchedulable=true;
					break;
				}
			}
			if(!isSchedulable){
				unschedulableTasksList.add(i);
			}
		}
		if(unschedulableTasksList.size()==0){
			return new ExecutionCostMatrix(executionCost, null);
		}
		float[][] executionCostWithoutUnschedulableTasks=new float[executionCost.length-unschedulableTasksList.size()][];
		int currentTask=0;
		for(int i=0;i<executionCost.length;i++){
			if(!unschedulableTasksList.contains(i)){
				executionCostWithoutUnschedulableTasks[currentTask]=executionCost[i];
				currentTask++;
			}
		}
		return new ExecutionCostMatrix(executionCostWithoutUnschedulableTasks, unschedulableTasksList);
	}

}
