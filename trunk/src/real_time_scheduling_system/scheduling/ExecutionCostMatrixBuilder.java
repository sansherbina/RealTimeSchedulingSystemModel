package real_time_scheduling_system.scheduling;

import java.util.ArrayList;

import real_time_scheduling_system.model.Machine;
import real_time_scheduling_system.model.Task;

public class ExecutionCostMatrixBuilder implements IExecutionCostMatrixBuilder{
	private ITaskOnMachineRelationCalculator taskOnMachineRelationCalculator;
	public ExecutionCostMatrixBuilder(ITaskOnMachineRelationCalculator taskOnMachineRelationCalculator){
		if(taskOnMachineRelationCalculator==null){
			throw new IllegalArgumentException();
		}
		this.taskOnMachineRelationCalculator=taskOnMachineRelationCalculator;
	}
	
	@Override
	public ExecutionCostMatrix buildExecutionCostMatrix(ArrayList<Task> tasks, ArrayList<Machine> machines) {
		if(tasks==null || machines==null || machines.size()==0 || tasks.size()==0){
			throw new IllegalArgumentException();
		}
		
		float[][] executionCost=new float[tasks.size()][];
		for(int i=0;i<executionCost.length;i++){
			executionCost[i]=new float[machines.size()];
			for(int j=0;j<executionCost[i].length;j++){
				if(machines.get(j).getAccessLevel()>=tasks.get(i).getAccessLevel()){
					double relation=taskOnMachineRelationCalculator.calculateTaskMachineRelation(tasks.get(i), machines.get(j));
					executionCost[i][j]=(float)(tasks.get(i).getRequestedExecutionTime()/relation);
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
		
		double[] machinesLoading=new double[machines.size()];
		for(int i=0;i<machinesLoading.length;i++){
			machinesLoading[i]=machines.get(i).calculateLoading();
		}
		
		double[] workTimePercentageForTask=new double[tasks.size()];
		for(int i=0;i<workTimePercentageForTask.length;i++){
			workTimePercentageForTask[i]=tasks.get(i).getWorkTimePercentage();
		}
		
		if(unschedulableTasksList.size()==0){
			return new ExecutionCostMatrix(executionCost, machinesLoading,workTimePercentageForTask, null);
		}
		float[][] executionCostWithoutUnschedulableTasks=new float[executionCost.length-unschedulableTasksList.size()][];
		int currentTask=0;
		for(int i=0;i<executionCost.length;i++){
			if(!unschedulableTasksList.contains(i)){
				executionCostWithoutUnschedulableTasks[currentTask]=executionCost[i];
				currentTask++;
			}
		}
		return new ExecutionCostMatrix(executionCostWithoutUnschedulableTasks, machinesLoading, workTimePercentageForTask, unschedulableTasksList);
	}


}
