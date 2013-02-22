package real_time_scheduling_system.model;

import java.util.ArrayList;

import real_time_scheduling_system.model.ExecutingTaskResult.ExecutingTaskStatus;

public class Machine extends MachineConfiguration {
	private ArrayList<Task> executingTasks;
	private float workTime;

	public Machine(int id, double memmoryCapacity, double[] processors,
			int accessLevel) {
		super(id, memmoryCapacity, processors, accessLevel);
		this.executingTasks = new ArrayList<Task>();
		this.workTime = 0;
	}

	public Machine(MachineConfiguration machineConfiguration) {
		super(machineConfiguration.getId(), machineConfiguration
				.getMemmoryCapacity(), machineConfiguration.getProcessors(),
				machineConfiguration.getAccessLevel());
		this.executingTasks = new ArrayList<Task>();
		this.workTime = 0;
	}

	public void operate(float timeInterval) {
		if (executingTasks.size() == 0) {
			return;
		}
		workTime += timeInterval;
		float timeForOneTask = timeInterval / executingTasks.size();
		for (int i = 0; i < executingTasks.size(); i++) {
			executingTasks.get(i).operateTask(timeForOneTask);
		}
	}

	public ArrayList<ExecutingTaskResult> getFinishedTasks() {
		ArrayList<ExecutingTaskResult> finishedTasks = new ArrayList<ExecutingTaskResult>();
		for (int i = executingTasks.size() - 1; i >= 0; i--) {
			if (executingTasks.get(i).isFinishOperatingTask()) {
				finishedTasks.add(new ExecutingTaskResult(
						executingTasks.get(i),
						ExecutingTaskStatus.EXECUTE_SUCCESFULL));
				executingTasks.remove(i);
			}
		}
		return finishedTasks;
	}

	public double calculateLoading() {
		double loading = 0;
		for (int i = 0; i < executingTasks.size(); i++) {
			loading += executingTasks.get(i).getWorkTimePercentage();
		}
		return loading;
	}

	public void addTask(Task task) {
		this.executingTasks.add(task);
	}

	public float getWorkTime() {
		return workTime;
	}
}
