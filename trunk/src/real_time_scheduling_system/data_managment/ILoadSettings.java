package real_time_scheduling_system.data_managment;

import java.io.InputStream;

import real_time_scheduling_system.model.MachineConfiguration;

public interface ILoadSettings {
	public MachineConfiguration[] loadCloudStucture(InputStream inputStream) throws IllegalArgumentException; 
	public ModelSettings loadModelSettings(InputStream inputStream) throws IllegalArgumentException; 
}
