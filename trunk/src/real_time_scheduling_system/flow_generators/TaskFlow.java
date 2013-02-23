package real_time_scheduling_system.flow_generators;

import java.util.ArrayList;
import java.util.List;

import real_time_scheduling_system.model.Task;

public class TaskFlow implements ITaskFlow {
	private IRandomGenerator randomGenerator;
	protected float minExecutionTime;
	protected float maxExecutionTime;
	protected double minProcessor;
	protected double maxProcessor;
	protected double minMemmoryCapacity;
	protected double maxMemmoryCapacity;
	protected float minWorkTimePercentage;
	protected float maxWorkTimePercentage;
	protected double minNewTaskSpeed;
	protected double maxNewTaskSpeed;
	protected int minAccessLevel;
	protected int maxAccessLevel;

	public TaskFlow(IRandomGenerator randomGenerator, float minExecutionTime,
			float maxExecutionTime, double minProcessor, double maxProcessor,
			double minMemmoryCapacity, double maxMemmoryCapacity,
			float minWorkTimePercentage, float maxWorkTimePercentage,
			double minNewTaskSpeed, double maxNewTaskSpeed, int minAccessLevel,
			int maxAccessLevel) {
		super();
		this.randomGenerator = randomGenerator;
		this.minExecutionTime = minExecutionTime;
		this.maxExecutionTime = maxExecutionTime;
		this.minProcessor = minProcessor;
		this.maxProcessor = maxProcessor;
		this.minMemmoryCapacity = minMemmoryCapacity;
		this.maxMemmoryCapacity = maxMemmoryCapacity;
		this.minWorkTimePercentage = minWorkTimePercentage;
		this.maxWorkTimePercentage = maxWorkTimePercentage;
		this.minNewTaskSpeed = minNewTaskSpeed;
		this.maxNewTaskSpeed = maxNewTaskSpeed;
		this.minAccessLevel = minAccessLevel;
		this.maxAccessLevel = maxAccessLevel;
	}

	public List<Task> generateTasks(double time) {
		int taskCount = (int) ((minNewTaskSpeed + (maxNewTaskSpeed - minNewTaskSpeed)
				* randomGenerator.nextRandomValue()) * time);
		List<Task> newTasks = new ArrayList<>();
		for (int i = 0; i < taskCount; i++) {
			double memmoryCapacity = minMemmoryCapacity
					+ (maxMemmoryCapacity - minMemmoryCapacity)
					* randomGenerator.nextRandomValue();
			double proccessor = minProcessor + (maxProcessor - minProcessor)
					* randomGenerator.nextRandomValue();
			float executionTime = minExecutionTime
					+ (float) ((maxExecutionTime - minExecutionTime) * randomGenerator
							.nextRandomValue());
			float workTimePercentage = minWorkTimePercentage
					+ (float) ((maxWorkTimePercentage - minWorkTimePercentage) * randomGenerator
							.nextRandomValue());
			int accessLevel = (int) (minAccessLevel + (maxAccessLevel - minAccessLevel)
					* randomGenerator.nextRandomValue());
			Task newTask = new Task(memmoryCapacity, proccessor, executionTime,
					workTimePercentage, accessLevel);
			newTasks.add(newTask);
		}
		return newTasks;
	}

}
