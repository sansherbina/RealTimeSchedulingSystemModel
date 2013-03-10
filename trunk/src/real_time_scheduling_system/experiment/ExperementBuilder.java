package real_time_scheduling_system.experiment;

import real_time_scheduling_system.experiment.IExperiment.ExperimentTypes;

public class ExperementBuilder {
	public static IExperiment buildExperiment(ExperimentTypes experimentType) {
		switch (experimentType) {
		case SYSTEM_LOAD_BY_INPUT_QUEUE:
			return new SystemLoadingByInputQueueLength();
		case FAILED_TASK_COUNT_BY_INPUT_QUEUE:
			return new FailedTaskCountByInputQueueSize();
		case FINISHED_TASK_COUNT_BY_INPUT_QUEUE:
			return new FinishedTaskCountByInputQueue();
		case SYSTEM_WORK_TIME_PERCENTAGE_BY_TASK_ACCESS_LEVEL:
			return new SystemWorkTimePercentageByTaskAccessLevel();
		}
		return null;
	}
}
