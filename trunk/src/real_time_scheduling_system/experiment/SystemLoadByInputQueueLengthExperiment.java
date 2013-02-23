package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.flow_generators.ITaskFlow;
import real_time_scheduling_system.flow_generators.TaskFlowBuilder;
import real_time_scheduling_system.model.CloudSystem;
import real_time_scheduling_system.model.ExecutedTasksHandler;
import real_time_scheduling_system.model.MachineConfiguration;
import real_time_scheduling_system.scheduling.ExecutionCostMatrixBuilder;
import real_time_scheduling_system.scheduling.ISchedulingAlgorithm;
import real_time_scheduling_system.scheduling.SchedulingAlgorithmBuilder;
import real_time_scheduling_system.scheduling.TaskOnMachineRelationCalculator;

public class SystemLoadByInputQueueLengthExperiment implements IExperiment {
	private static final int MODELING_ITERVAL_COUNT = 1000;

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		ExecutedTasksHandler tasksHandler = new ExecutedTasksHandler();
		ITaskFlow taskFlow = TaskFlowBuilder.buildTaskFlow(modelSettings);
		ExecutionCostMatrixBuilder executionCostMatrixBuilder = new ExecutionCostMatrixBuilder(
				new TaskOnMachineRelationCalculator());
		ISchedulingAlgorithm schedulingAlgorithm = SchedulingAlgorithmBuilder
				.buildSchedulingAlgorithm(modelSettings
						.getSchedulingAlgorithmType());
		CloudSystem cloudSystem = new CloudSystem(machineConfigurations,
				modelSettings.getTaskLoadingTimeInterval(),
				modelSettings.getTaskLoadingCountBorder(), tasksHandler,
				taskFlow, executionCostMatrixBuilder, schedulingAlgorithm);
		DataMass dataMass = new DataMass(MODELING_ITERVAL_COUNT);
		float modelingTimeInterval = modelSettings.getModelingTime()
				/ MODELING_ITERVAL_COUNT;
		for (int i = 0; i < MODELING_ITERVAL_COUNT; i++) {
			cloudSystem.modelSystem(modelingTimeInterval);
			dataMass.addData(i, cloudSystem.getInputTaskBufferSize(),
					cloudSystem.getAvverageMachinesWorkLoad());
		}
		return dataMass;
	}

}
