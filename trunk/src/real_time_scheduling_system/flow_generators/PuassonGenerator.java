package real_time_scheduling_system.flow_generators;

import java.util.Random;

public class PuassonGenerator implements IRandomGenerator {

    private IRandomGenerator lcgGen;

    private double lyambda;

    private double b;

    public PuassonGenerator() {
        this.lyambda = 3.0;
        this.lcgGen = new LCGenerator();
        this.b = 0.0;
    }

    public PuassonGenerator(double lyambda) {
        this.lyambda = lyambda;
        this.lcgGen = new LCGenerator();
        this.b = 0.0;
    }

    public PuassonGenerator(double lyambda, LCGenerator lcgGen) {
        this.lyambda = lyambda;
        this.lcgGen = lcgGen;
        this.b = 0.0;
    }

    public double nextRandomValue() {
        double res;
        res = -Math.log(1.0 - lcgGen.nextRandomValue()) / lyambda;
        if (res > b) {
            b = res;
        }

        return res;
    }

}
