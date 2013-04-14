package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.IChart;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.data_managment.DataMass;

public interface IExperiment {
	enum ExperimentTypes {
		IDLE_TIME_BY_INPUT_QUEUE_LENGTH("SystemIdleTimeByInputQueueLength", "InputQueueSize",
				"Idle time", IChart.SPLINE_CHART_TYPE),
		SYSTEM_LOADING_BY_INPUT_QUEUE_LENGTH("SystemLoadingByInputQueueLength", "InputQueueSize",
				"System loading", IChart.SPLINE_CHART_TYPE),
		IDLE_TIME_BY_PRIORITY("IdleTimeByPriority", "Priority",
				"IdleTime", IChart.DUAL_BAR_CHART_TYPE),
		INPUT_QUEUE_LENGTH_BY_TIME("InputQueueLengthByTime", "Time",
				"InputQueueLength", IChart.SPLINE_CHART_TYPE),
		FAILED_TASK_COUNT_BY_INPUT_QUEUE_LENGTH("FailedTaskCountByInputQueueLength", "InputQueueLength", 
				"FailedTaskCount", IChart.SPLINE_CHART_TYPE),
		FAILED_TASK_COUNT_PROBABILITY_BY_INPUT_QUEUE_LENGTH("FailedTaskCountProbabilityByInputQueueLength", "InputQueueLength", 
				"FailedTaskCountProbability", IChart.SPLINE_CHART_TYPE),
		RESOURCES_AMOUNT_BY_SYSTEM_LOAD("ResourcesAmountBySystemLoad", "SystemLoad", 
				"ResourcesAmount", IChart.SPLINE_CHART_TYPE),
		RESOURCES_AMOUNT_BY_INPUT_QUEUE_LENGTH("ResourcesAmountyByInputQueueLength", "InputQueueLength", 
				"ResourcesAmount", IChart.SPLINE_CHART_TYPE),
		SYSTEM_LOAD_BY_SCALABILITY_LEVEL("SystemLoadByScalabilityLevel","ScalabilityLevel",
				"SystemLoad", IChart.SPLINE_CHART_TYPE),
		FAIL_PROBABILITY_BY_SCALABILITY_LEVEVEL("FailProbabilityByScalabilityLevel","ScalabilityLevel",
				"FailProbability", IChart.SPLINE_CHART_TYPE);
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
