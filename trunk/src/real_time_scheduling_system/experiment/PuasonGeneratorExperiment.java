package real_time_scheduling_system.experiment;

import java.util.List;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.data_managment.ModelSettings;
import real_time_scheduling_system.flow_generators.IRandomGenerator;
import real_time_scheduling_system.flow_generators.NormalGenerator;
import real_time_scheduling_system.flow_generators.PuassonGenerator;
import real_time_scheduling_system.model.MachineConfiguration;

public class PuasonGeneratorExperiment implements IExperiment{

	@Override
	public DataMass makeExperiment(ModelSettings modelSettings,
			List<MachineConfiguration> machineConfigurations) {
		IRandomGenerator randomGenerator=new PuassonGenerator();
		return GeneratorModeler.modelGenerator(randomGenerator);
	}
	
}
