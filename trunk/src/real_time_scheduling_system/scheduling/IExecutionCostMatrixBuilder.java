package real_time_scheduling_system.scheduling;

public interface IExecutionCostMatrixBuilder {
	float PROHIBITED_EXECUTION=-1f;
	public float[][] buildExecutionCostMatrix(Task[] tasks, Machine[] machines, Integer[] unschedulableTasks);
}
