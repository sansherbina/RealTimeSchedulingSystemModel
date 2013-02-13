package real_time_scheduling_system.data_managment;

import java.io.InputStream;

import real_time_scheduling_system.scheduling.Machine;

public interface ILoadSettings {
	public Machine[] loadCloudStucture(InputStream inputStream) throws IllegalArgumentException; 
}
