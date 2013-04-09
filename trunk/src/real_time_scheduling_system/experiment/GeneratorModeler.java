package real_time_scheduling_system.experiment;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.flow_generators.IRandomGenerator;

public class GeneratorModeler {
	public static DataMass modelGenerator(IRandomGenerator generator){
		int experimentCount=100000;
		int partCount=50;
		int[] partCountArray=new int[partCount+1];
		double interval=((double)1)/partCount;
		double[] randomValues=new double[experimentCount];
		double max=0;
		for(int i=0;i<experimentCount;i++){
			double randomValue=generator.nextRandomValue();
			randomValues[i]=randomValue;
			if(randomValue>max){
				max=randomValue;
			}
		}
		for(int i=0;i<experimentCount;i++){
			randomValues[i]=randomValues[i]/max;
			int partNumber=(int)(randomValues[i]/interval);
			partCountArray[partNumber]++;
		}

		//DataMass dataMass=new DataMass(2*partCount);
		DataMass dataMass=new DataMass(partCount);
		for(int i=0;i<partCount;i++){
			dataMass.addData(i, 1000*interval*i, 1000*((double)partCountArray[i])/experimentCount);
		}
		/*
		for(int i=0;i<partCount;i++){
			double y=1000*((double)partCountArray[i])/experimentCount;
			if(Math.random()<0.5){
				y+=Math.random()*y/10;
			}else{
				y-=Math.random()*y/10;
			}
			dataMass.addData(partCount+i, 1000*(interval*(i+partCount)), y);
		}*/
		return dataMass;
	}
}
