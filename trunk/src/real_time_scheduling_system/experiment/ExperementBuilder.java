package real_time_scheduling_system.experiment;

import real_time_scheduling_system.experiment.IExperiment.ExperimentTypes;

public class ExperementBuilder {
	public static IExperiment buildExperiment(ExperimentTypes experimentType) {
		switch (experimentType) {
		case WAIT_TIME_BY_INPUT_QUEUE_LENGTH:
			return new WaitTimeByInputQueueSize();
		case IDLE_TIME_BY_INPUT_QUEUE_LENGTH:
			return new IdleTimeByInputQueueLength();
		case NORMAL_GENERATOR:
			return new NormalGeneratorExperiment();
		case ERLANG_GENERATOR:
			return new ErlangGeneratorExperiment();
		case PUASSON_GENERATOR:
			return new PuasonGeneratorExperiment();
		}
		return null;
	}
}
