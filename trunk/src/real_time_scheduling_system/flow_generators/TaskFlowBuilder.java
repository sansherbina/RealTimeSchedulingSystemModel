package real_time_scheduling_system.flow_generators;

public class TaskFlowBuilder {
	public static final int NORMAL_DISTRIBUTION_TASK_FLOW_TYPE = 1;
	public static final int UNIFORM_DISTRIBUTION_TASK_FLOW_TYPE = 2;

	public static ITaskFlow buildTaskFlow(int flowType) {
		switch (flowType) {
		case NORMAL_DISTRIBUTION_TASK_FLOW_TYPE:
			return new NormalDistributionTaskFlow();
		case UNIFORM_DISTRIBUTION_TASK_FLOW_TYPE:
			return new UniformDistributionTaskFlow();
		}
		return new UniformDistributionTaskFlow();
	}
}
