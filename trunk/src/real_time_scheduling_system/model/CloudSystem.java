package real_time_scheduling_system.model;

import java.util.ArrayList;

public class CloudSystem {
	private static final float MODELING_TIME_INTERVAL=1;
	private ArrayList<Machine> machines;
	private ArrayList<Task> executingTasks;
	private float currentTime;
	private ArrayList<Task> inputTaskBuffer;
	private float lastTaskLoadingTime;
	private float taskLoadingTimeInterval;
	private float taskLoadingCountBorder;
	
	public CloudSystem(ArrayList<MachineConfiguration> machineConfigurations, 
			float taskLoadingTimeInterval, float taskLoadingCountBorder){
		if(machineConfigurations==null || machineConfigurations.size()==0){
			throw new IllegalArgumentException();
		}
		this.taskLoadingCountBorder=taskLoadingCountBorder;
		this.taskLoadingTimeInterval=taskLoadingTimeInterval;
		this.machines=new ArrayList<Machine>();
		this.executingTasks=new ArrayList<Task>();
		this.inputTaskBuffer=new ArrayList<Task>();
		this.currentTime=0;
		for(int i=0;i<machineConfigurations.size();i++){
			machines.add(new Machine(machineConfigurations.get(i)));
		}
	}
	
	private void modelSystem(){
		
	}
	
	public void modelSystem(float modelingTime){
		if(modelingTime<MODELING_TIME_INTERVAL){
			throw new IllegalArgumentException();
		}
		int iterationCount=(int)(modelingTime/MODELING_TIME_INTERVAL);
		for(int i=0;i<iterationCount;i++){
			modelSystem();
		}
	}
}
