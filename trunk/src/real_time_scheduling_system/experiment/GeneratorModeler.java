package real_time_scheduling_system.experiment;

import real_time_scheduling_system.data_managment.DataMass;
import real_time_scheduling_system.flow_generators.IRandomGenerator;

public class GeneratorModeler {
	public static DataMass modelGenerator(IRandomGenerator generator){
		int experimentCount=1000000;
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

		DataMass dataMass=new DataMass(partCount);
		for(int i=0;i<partCount;i++){
			dataMass.addData(i, interval*i, ((double)partCountArray[i])/experimentCount);
		}
		return dataMass;
	}
}
