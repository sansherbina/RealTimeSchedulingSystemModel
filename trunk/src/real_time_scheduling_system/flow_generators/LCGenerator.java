package real_time_scheduling_system.flow_generators;

import java.util.Random;

public class LCGenerator implements IRandomGenerator {

    private double prevValue;

    private double a;

    private double c;

    private double d;

    public LCGenerator() {
        a = Math.pow(5, 17);
        c = Math.pow(3, 3);
        d = Math.pow(2, 32);
        prevValue = (double) new Random().nextInt((int)d);
    }

    public LCGenerator(double a, double c, double d, double w0) {
        this.a = a;
        this.c = c;
        this.d = d;
        this.prevValue = w0;
    }

    public double nextRandomValue() {
        double res;
        res = (a * prevValue + c) % d;
        prevValue = res;

        return res / d;
    }

}
