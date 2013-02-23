package real_time_scheduling_system.experiment;

public class ExperimentNumberSingleton {
	private static ExperimentNumberSingleton experimentNumberSingleton;
	private int number;

	private ExperimentNumberSingleton() {
		number = 0;
	}

	public static synchronized int getNumber() {
		if (experimentNumberSingleton == null) {
			experimentNumberSingleton = new ExperimentNumberSingleton();
		}
		int resultNumber = experimentNumberSingleton.number;
		experimentNumberSingleton.number++;
		return resultNumber;
	}
}
