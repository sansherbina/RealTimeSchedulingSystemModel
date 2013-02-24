package real_time_scheduling_system.experiment;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import real_time_scheduling_system.data_managment.Charts;
import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.IChart;
import real_time_scheduling_system.data_managment.ILoadSettings;
import real_time_scheduling_system.data_managment.LoadSettings;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.data_managment.XmlException;
import real_time_scheduling_system.experiment.IExperiment.ExperimentTypes;
import real_time_scheduling_system.model.MachineConfiguration;

public class SystemExperementManager {
	public static SystemExperementResult makeExperements(
			String cloudStructureFilePath, String modelingSettingsFile,
			List<ExperimentTypes> experimentTypes)
			throws IllegalArgumentException {
		ILoadSettings settingsLoader = new LoadSettings();
		List<MachineConfiguration> machineConfigurations = null;
		try {
			machineConfigurations = settingsLoader
					.loadCloudStucture(cloudStructureFilePath);
		} catch (IOException | SAXException | ParserConfigurationException
				| XmlException e) {
			throw new IllegalArgumentException();
		}
		ModelSettings modelSettings = null;
		try {
			modelSettings = settingsLoader
					.loadModelSettings(modelingSettingsFile);
		} catch (IOException | SAXException | ParserConfigurationException
				| XmlException e) {
			throw new IllegalArgumentException();
		}
		SystemExperementResult systemExperementResult = new SystemExperementResult();
		int experimentsNumber = ExperimentNumberSingleton.getNumber();
		IChart chartBuilder = new Charts();
		for (ExperimentTypes experimentType : experimentTypes) {
			IExperiment experiment = ExperementBuilder
					.buildExperiment(experimentType);
			DataMass dataMass = experiment.makeExperiment(modelSettings,
					machineConfigurations);
			String chartName = experimentType.name
					+ Integer.valueOf(experimentsNumber).toString();
			try {
				chartBuilder.getSplineChart(dataMass, chartName,
						experimentType.xName, experimentType.yName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			systemExperementResult.addGraphicFile(experimentType, chartName);
		}
		return systemExperementResult;
	}
}