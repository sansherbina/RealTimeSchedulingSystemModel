package real_time_scheduling_system.experiment;

import real_time_scheduling_system.experiment.IExperiment.ExperimentTypes;

public class ExperementBuilder {
	public static IExperiment buildExperiment(ExperimentTypes experimentType) {
		switch (experimentType) {
		case SYSTEM_LOAD_BY_INPUT_QUEUE:
			return new SystemLoadByInputQueueLengthExperiment();
		}
		return null;
	}
}
