package real_time_scheduling_system.experiment;

import java.util.HashMap;
import java.util.Map;

import real_time_scheduling_system.experiment.IExperiment.ExperimentTypes;

public class SystemExperementResult {
	private Map<ExperimentTypes, String> graphicFiles;

	public SystemExperementResult() {
		this.graphicFiles = new HashMap<>();
	}

	public void addGraphicFile(ExperimentTypes experimentType, String filePath) {
		this.graphicFiles.put(experimentType, filePath);
	}

	public Map<ExperimentTypes, String> getGraphicFiles() {
		return graphicFiles;
	}
}
