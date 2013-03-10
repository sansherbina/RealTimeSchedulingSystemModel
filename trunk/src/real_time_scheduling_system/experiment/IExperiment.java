package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.IChart;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.data_managment.DataMass;

public interface IExperiment {
	enum ExperimentTypes {
		SYSTEM_LOAD_BY_INPUT_QUEUE("SystemLoadingByInputQueue",
				"InputQueueSize", "SystemLoading", IChart.SPLINE_CHART_TYPE), FAILED_TASK_COUNT_BY_INPUT_QUEUE(
				"FailedTaskCountByInputQueue", "InputQueueSize",
				"FailedTaskCount", IChart.SPLINE_CHART_TYPE), FINISHED_TASK_COUNT_BY_INPUT_QUEUE(
				"FinishedTaskCountByInputQueue", "InputQueueSize",
				"FinishedTaskCount", IChart.SPLINE_CHART_TYPE), SYSTEM_WORK_TIME_PERCENTAGE_BY_TASK_ACCESS_LEVEL(
				"SystemWorkTimePercentageByTaskAccessLevel", "Task Priority",
				"SystemWorkTimePercentage", IChart.DUAL_BAR_CHART_TYPE);
		public String name;
		public String xName;
		public String yName;
		public int chartType;

		private ExperimentTypes(String name, String xName, String yName,
				int chartType) {
			this.name = name;
			this.xName = xName;
			this.yName = yName;
			this.chartType = chartType;
		}
	}

	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations);
}
