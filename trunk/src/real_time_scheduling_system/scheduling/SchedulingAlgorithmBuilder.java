package real_time_scheduling_system.scheduling;

import real_time_scheduling_system.scheduling.a_scheduling_algorithm.ASchedulingAlgorithm;

public class SchedulingAlgorithmBuilder {
	public static final int ASCHEDULING_ALGORITHM = 1;

	public static ISchedulingAlgorithm buildSchedulingAlgorithm(
			int schedulingAlgorithmType) {
		switch (schedulingAlgorithmType) {
		case ASCHEDULING_ALGORITHM:
			return new ASchedulingAlgorithm();
		}
		return new ASchedulingAlgorithm();
	}
}
