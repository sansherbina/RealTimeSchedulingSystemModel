package real_time_scheduling_system.flow_generators;

import real_time_scheduling_system.data_managment.ModelSettings;

public class TaskFlowBuilder {
	public static final int NORMAL_DISTRIBUTION_TASK_FLOW_TYPE = 1;
	public static final int UNIFORM_DISTRIBUTION_TASK_FLOW_TYPE = 2;

	public static ITaskFlow buildTaskFlow(ModelSettings modelSettings) {
		IRandomGenerator randomGenerator = null;
		switch (modelSettings.getInputTaskFlowType()) {
		case NORMAL_DISTRIBUTION_TASK_FLOW_TYPE:
			randomGenerator = new NormalGenerator(
					modelSettings.getNormalDistributionM(),
					modelSettings.getNormalDistributionSigma());
			break;
		case UNIFORM_DISTRIBUTION_TASK_FLOW_TYPE:
			randomGenerator = new UniformGenrator();
			break;
		default:
			randomGenerator = new UniformGenrator();
		}
		TaskFlow taskFlow = new TaskFlow(randomGenerator,
				modelSettings.getMinimumTaskTime(),
				modelSettings.getMaximumTaskTime(),
				modelSettings.getMinProcessor(),
				modelSettings.getMaxProcessor(),
				modelSettings.getMinMemmoryCapacity(),
				modelSettings.getMaxMemmoryCapacity(),
				modelSettings.getMinWorkTimePercentage(),
				modelSettings.getMaxWorkTimePercentage(),
				modelSettings.getMinNewTaskSpeed(),
				modelSettings.getMaxNewTaskSpeed(),
				modelSettings.getMinAccessLevel(),
				modelSettings.getMaxAccessLevel());
		return taskFlow;
	}
}
