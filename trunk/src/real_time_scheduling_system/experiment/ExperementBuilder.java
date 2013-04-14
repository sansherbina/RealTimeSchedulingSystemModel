package real_time_scheduling_system.experiment;

import real_time_scheduling_system.experiment.IExperiment.ExperimentTypes;

public class ExperementBuilder {
	public static IExperiment buildExperiment(ExperimentTypes experimentType) {
		switch (experimentType) {
		case IDLE_TIME_BY_INPUT_QUEUE_LENGTH:
			return new WaitTimeByInputQueueSize();
		case SYSTEM_LOADING_BY_INPUT_QUEUE_LENGTH:
			return new SystemLoadingByInputQueueLength();
		case IDLE_TIME_BY_PRIORITY:
			return new SystemWorkTimePercentageByTaskAccessLevel();
		case INPUT_QUEUE_LENGTH_BY_TIME:
			return new ErlangGeneratorExperiment();
		case FAILED_TASK_COUNT_BY_INPUT_QUEUE_LENGTH:
			return new FailedTaskCountByInputQueueSize();
		case FAILED_TASK_COUNT_PROBABILITY_BY_INPUT_QUEUE_LENGTH:
			return new FailedTaskCountProbabilityByInputQueueSize();
		case RESOURCES_AMOUNT_BY_SYSTEM_LOAD:
			return new ResourcesAmountBySystemLoad();
		case RESOURCES_AMOUNT_BY_INPUT_QUEUE_LENGTH:
			return new ResourcesAmountByInputQueueLength();
		case SYSTEM_LOAD_BY_SCALABILITY_LEVEL:
			return new SystemLoadByScalabilityLevel();
		case FAIL_PROBABILITY_BY_SCALABILITY_LEVEVEL:
			return new FailProbabilityByScalabilityLevel();
		}
		return null;
	}
}
