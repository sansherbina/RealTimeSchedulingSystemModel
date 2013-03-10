package real_time_scheduling_system.flow_generators;

import java.util.List;

import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.Task;

public class Test {
	public static void main(String[] args){
		ModelSettings modelSettings=new ModelSettings(1, 10, 1, 5f, 10f, 1, 1000f, 0.5d, 0.3d, 1d, 4d, 1000d, 4000d, 0.1f, 0.8f, 0.1d, 2d, 1, 10);
		ITaskFlow taskFlow=TaskFlowBuilder.buildTaskFlow(modelSettings);
		List<Task> tasks=taskFlow.generateTasks(10f);
		for(Task task:tasks){
			System.out.println(task.toString());
		}
	}
}
