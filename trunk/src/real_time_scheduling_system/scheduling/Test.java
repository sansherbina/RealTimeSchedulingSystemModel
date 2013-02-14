package real_time_scheduling_system.scheduling;

import java.util.HashMap;

import real_time_scheduling_system.scheduling.a_scheduling_algorithm.ASchedulingAlgorithm;

public class Test {
	public static void main(String[] args){
		Machine[] machines=new Machine[]{
				new Machine(0, 10, new double[]{10,15}, 0),
				new Machine(1, 10, new double[]{10,15}, 1),
				new Machine(2, 10, new double[]{10,15}, 2)
		};
		Task[] tasks=new Task[]{
				new Task(0,0,10d,10d,10d,10),
				new Task(0,1,5d,5d,5d,10),
				new Task(2,2,20d,21d,20d,10),
				new Task(2,3,50d,5d,5d,10),
				new Task(0,4,20d,26d,20d,10)
		};
		ASchedulingAlgorithm algorithm=new ASchedulingAlgorithm(new ExecutionCostMatrixBuilder());
		HashMap<Integer, Integer> scheduling=algorithm.scheduleTask(tasks, machines);
		System.out.println(scheduling.toString());
	}
}
