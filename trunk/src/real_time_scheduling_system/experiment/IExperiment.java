package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.IChart;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.data_managment.DataMass;

public interface IExperiment {
	enum ExperimentTypes {
		WAIT_TIME_BY_INPUT_QUEUE_LENGTH("SystemWaitTimeByInputQueueLength", "InputQueueSize",
				"Wait time", IChart.SPLINE_CHART_TYPE),
		IDLE_TIME_BY_INPUT_QUEUE_LENGTH("SystemIdleTimeByInputQueueLength", "InputQueueSize",
				"Idle time", IChart.SPLINE_CHART_TYPE),
		NORMAL_GENERATOR("Normal distribution", "Probability",
				"Percentage", IChart.SPLINE_CHART_TYPE),
		ERLANG_GENERATOR("Erlang distribution", "Probability",
				"Percentage", IChart.SPLINE_CHART_TYPE),
		PUASSON_GENERATOR("Puasson distribution", "Probability",
				"Percentage", IChart.SPLINE_CHART_TYPE);
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
